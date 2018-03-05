package com.test.rssapp.network.model.response;

import com.google.gson.annotations.SerializedName;
import org.parceler.Parcel;

@Parcel
public class Article {

    @SerializedName("url")          String mLink;
    @SerializedName("title")        String mTitle;
    @SerializedName("urlToImage")   String mImage;
    @SerializedName("source")       Source mSource;
    @SerializedName("description")  String mDescription;
    @SerializedName("publishedAt")  String mPublicationDate;

    public String getLink() {
        return mLink;
    }
    public String getTitle() {
        return mTitle;
    }
    public String getImage() {
        return mImage;
    }
    public Source getSource() {
        return mSource;
    }
    public String getDescription() {
        return mDescription;
    }
    public String getPublicationDate() {
        return mPublicationDate;
    }


    @Parcel
    public static class Source {
        @SerializedName("id")   String mId;
        @SerializedName("name") String mName;

        public String getId() {
            return mId;
        }
        public String getName() {
            return mName;
        }
    }
}

