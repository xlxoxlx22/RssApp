package com.test.rssapp.network.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseObject {

    @SerializedName("status") private String mStatus = null;
    @SerializedName("totalResults") private int mTotalResultsCount;
    @SerializedName("articles") private List<Article> mArticles = null;


    public List<Article> getArticles() {
        return mArticles;
    }

    public int getTotalResultsCount() { return mTotalResultsCount;}


    public boolean getStatus() {
        if (mStatus != null && mStatus.equalsIgnoreCase("ok")) {
            return true;
        } else {
            return false;
        }
    }

}

