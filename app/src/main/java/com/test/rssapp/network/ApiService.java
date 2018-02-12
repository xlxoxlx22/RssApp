package com.test.rssapp.network;


import com.test.rssapp.network.model.RequestResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface ApiService {

    @GET("api.json")
    Observable<RequestResponse> getRssList(@Query("rss_url") String query);

}
