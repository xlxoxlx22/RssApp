package com.test.rssapp.network.model;


import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Article {

    @SerializedName("url")          String mUrl;
    @SerializedName("title")        String mTitle;
    @SerializedName("link")         String mLink;
    @SerializedName("thumbnail")    String mImage;
    @SerializedName("description")  String mDescription;
    @SerializedName("enclosure")    Enclosure mEnclosure;
    @SerializedName("pubDate")      String mPublicationDate;

    public String getUrl() {
        return mUrl;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getLink() {
        return mLink;
    }

    public String getPublicationDate() {
        return mPublicationDate;
    }

    public String getImage() {
        return mImage;
    }

    public String getDescription() {
        return mDescription;
    }

    public Enclosure getEnclosure() {
        return mEnclosure;
    }




    @Parcel
    public static class Enclosure {
        @SerializedName("link") String mLink;
        @SerializedName("thumbnail") String mThumbnail;



        public String getImageUrl() {
            if (TextUtils.isEmpty(mThumbnail)) {
                return mLink;
            } else {
                return mThumbnail;
            }
        }
    }
}

