package com.test.rssapp.network.model;


import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Article {

    @SerializedName("url")          String mUrl;
    @SerializedName("title")        String mTitle;

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

    @SerializedName("link")         String mLink;
    @SerializedName("pubDate")      String mPublicationDate;
    @SerializedName("thumbnail")    String mImage;
    @SerializedName("description")  String mDescription;
    @SerializedName("enclosure")    Enclosure mEnclosure;


    @Parcel
    public class Enclosure {
        @SerializedName("link") String mLink;

        public String getLink() {
            return mLink;
        }
    }
}

