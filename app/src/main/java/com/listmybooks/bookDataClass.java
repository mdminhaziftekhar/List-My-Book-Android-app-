package com.listmybooks;

public class bookDataClass {
    private String mbookTitle;
    private String mbookAuthor;
    private String mbookDate;
    private String mImageLink;

    public  bookDataClass(String mImageLink, String mbookTitle, String mbookAuthor, String mbookDate){
        this.mImageLink = mImageLink;
        this.mbookTitle = mbookTitle;
        this.mbookAuthor = mbookAuthor;
        this.mbookDate = mbookDate;
    }

    public String getmImageLink() {
        return mImageLink;
    }

    public String getMbookTitle() {
        return mbookTitle;
    }

    public String getMbookAuthor() {
        return mbookAuthor;
    }

    public String getMbookDate() {
        return mbookDate;
    }
}
