package com.listmybooks;

import static com.listmybooks.MainActivity.Request_URL;

public class bookDataClass {
    private String mbookTitle;
    private String mbookAuthor;
    private String mbookDate;
    private String mImageLink;
    private String URL = Request_URL;

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

    public String getURL() {
        return URL;
    }
}
