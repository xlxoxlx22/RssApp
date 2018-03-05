package com.test.rssapp.network;


import com.test.rssapp.network.model.request.RequestObject;
import com.test.rssapp.network.model.response.ResponseObject;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {

    @Headers("X-Api-Key:057c3271583b48caa753ac0891fcb819")
    @GET("top-headlines")
    Observable<ResponseObject> getRssList(@Query("sources") String source);
}
