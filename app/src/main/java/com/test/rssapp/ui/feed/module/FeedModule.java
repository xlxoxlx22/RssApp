package com.test.rssapp.ui.feed.module;
import com.test.rssapp.di.scope.ActivityScope;
import com.test.rssapp.data.AppPreferences;
import com.test.rssapp.network.ApiService;
import com.test.rssapp.ui.feed.FeedPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class FeedModule {

    @Provides
    @ActivityScope
    public FeedPresenter providesFeedPresenter(ApiService apiService,
                                               AppPreferences appPreferences) {
        return new FeedPresenter(
                apiService,
                appPreferences);
    }
}
