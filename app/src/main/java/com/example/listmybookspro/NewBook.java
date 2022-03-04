package com.example.listmybookspro;

import android.os.Parcel;
import android.os.Parcelable;

public class NewBook implements Parcelable {

    public static final Parcelable.Creator<NewBook> CREATOR = new Parcelable.Creator<NewBook>() {
        @Override
        public NewBook createFromParcel(Parcel source) {
            return new NewBook(source);
        }

        @Override
        public NewBook[] newArray(int size) {
            return new NewBook[size];
        }
    };
    /**
     * Title of the book
     */
    private final String title;
    /**
     * Author of the book
     */
    private final String author;
    /**
     * URL address of an image cover of the book
     */
    private final String imageUrl;

    /**
     * Url of the book
     */
    private String urlBook;

    /**
     * @param bookTitle     - (String) name of the book i.e.: "Harry Potter i Kamień Filozoficzny"
     * @param authorName    - (String) name of author i.e.: "J.K. Rowling"
     * @param urlImageCover - (String) URL address of an image cover i.e.: "http://books.google.com/books/(...)"
     * @param buyLink       - (String) url for buying page on Google Play
     */
    public NewBook(String bookTitle, String authorName, String urlImageCover, String buyLink) {
        this.title = bookTitle;
        this.author = authorName;
        this.imageUrl = urlImageCover;

        this.urlBook = buyLink;

    }

    protected NewBook(Parcel in) {
        this.title = in.readString();
        this.author = in.readString();
        this.imageUrl = in.readString();
        this.urlBook = in.readString();

    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getUrlBook() {
        return urlBook;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.author);
        dest.writeString(this.imageUrl);
        dest.writeString(this.urlBook);

    }
}
