package com.test.rssapp.network.model.request;


import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;


public class RequestObject {
    @SerializedName("sources")
    private String mSource;

    @SerializedName("country")
    private String mCountry;

    @SerializedName("q")
    private String mQuery;

    public RequestObject(String mSource){
        this.mSource = mSource;
    }

    public RequestObject(String mSource, String mCountry){
        this.mSource = mSource;
        this.mCountry = mCountry;
    }

    public RequestObject(String mSource, String mCountry, String query){
        this.mQuery = query;
        this.mSource = mSource;
        this.mCountry = mCountry;
    }

//    public String buildRequest(){
//
//
//        StringBuilder stringBuilder = new StringBuilder();
//
//        if (!TextUtils.isEmpty(mSource)) {
//            stringBuilder.append("sources").append(mSource);
//        }
//
//        if (!TextUtils.isEmpty(mCountry)) {
//            stringBuilder.append("&").append(mCountry);
//        }
//
//        if (!TextUtils.isEmpty(mQuery)) {
//            stringBuilder.append("&").append(mQuery);
//        }
//    }

}
