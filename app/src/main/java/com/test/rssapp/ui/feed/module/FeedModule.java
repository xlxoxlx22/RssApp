package com.test.rssapp.ui.feed.module;


import com.test.rssapp.di.scope.ApplicationScope;
import com.test.rssapp.network.ApiService;
import com.test.rssapp.ui.feed.FeedPresenter;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class FeedModule {

    @Provides
    @ApplicationScope
    public FeedPresenter providesFeedPresenter(){
        return new FeedPresenter();
    }

    @Provides
    @ApplicationScope
    public ApiService providesApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }
}
