package com.test.rssapp.ui.detail.module;

import com.test.rssapp.di.scope.ActivityScope;
import com.test.rssapp.ui.detail.DetailPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class DetailModule {

    @Provides
    @ActivityScope
    public DetailPresenter providesFeedPresenter() {
        return new DetailPresenter();
    }
}
