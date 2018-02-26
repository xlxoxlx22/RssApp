package com.test.rssapp.ui.base;

import com.test.rssapp.di.scope.ApplicationScope;
import com.test.rssapp.network.NetworkModule;
import com.test.rssapp.ui.detail.DetailActivity;
import com.test.rssapp.ui.feed.FeedActivity;

import dagger.Component;

@Component(modules = {AppModule.class, NetworkModule.class})
@ApplicationScope
public interface AppComponent {
    void inject(FeedActivity feedActivity);
    void inject(DetailActivity detailActivity);
}
