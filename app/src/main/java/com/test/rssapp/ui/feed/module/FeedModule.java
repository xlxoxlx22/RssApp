package com.test.rssapp.ui.feed.module;
import android.content.Context;

import com.test.rssapp.data.AppEventBus;
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
    public FeedPresenter providesFeedPresenter(Context context,
                                               AppEventBus appEventBus,
                                               ApiService apiService,
                                               AppPreferences appPreferences) {
        return new FeedPresenter(
                context,
                appEventBus,
                apiService,
                appPreferences);
    }
}
