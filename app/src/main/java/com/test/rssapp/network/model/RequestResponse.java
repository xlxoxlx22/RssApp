package com.test.rssapp.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestResponse {

    @SerializedName("items") private List<Article> mArticles = null;
    @SerializedName("status") private String mStatus = null;
    @SerializedName("feed") private Feed mFeed = null;


    public List<Article> getArticles() {
        return mArticles;
    }
    public Feed getFeed() {
        return mFeed;
    }

    public boolean getStatus() {
        if (mStatus != null && mStatus.equalsIgnoreCase("ok")) {
            return true;
        } else {
            return false;
        }
    }

}

