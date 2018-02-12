package com.test.rssapp.network.model;


import com.google.gson.annotations.SerializedName;

public class Feed {

    @SerializedName("url")          String mUrl;
    @SerializedName("title")        String mTitle;
    @SerializedName("link")         String mLink;
    @SerializedName("description")  String mDescription;
    @SerializedName("image")        String mImage;

    public String getUrl() {
        return mUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getLink() {
        return mLink;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getImage() {
        return mImage;
    }
}
