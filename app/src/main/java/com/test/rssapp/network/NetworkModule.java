package com.test.rssapp.network;


import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.rssapp.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String BASE_URL = "https://api.rss2json.com/v1/";

    public NetworkModule() {}

    @Provides
    @ApplicationScope
    public Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @ApplicationScope
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @ApplicationScope
    public OkHttpClient provideOkHttpClient(Cache cache) {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .cache(cache)
                .build();
        return client;
    }


    @Provides
    @ApplicationScope
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }

    @Provides
    @ApplicationScope
    public ApiService provideApiService(Retrofit retrofit){
        return retrofit.create(ApiService.class);
    }




}
