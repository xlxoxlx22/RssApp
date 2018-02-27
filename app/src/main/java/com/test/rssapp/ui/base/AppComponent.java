package com.test.rssapp.ui.base;

import com.test.rssapp.di.scope.ApplicationScope;
import com.test.rssapp.helpers.SharedPreferencesModule;
import com.test.rssapp.network.NetworkModule;
import com.test.rssapp.ui.detail.DetailActivity;
import com.test.rssapp.ui.feed.FeedActivity;
import com.test.rssapp.ui.feed.module.FeedComponent;
import com.test.rssapp.ui.feed.module.FeedModule;

import dagger.Component;

@ApplicationScope
@Component(
        modules = {
        AppModule.class,
        NetworkModule.class,
        SharedPreferencesModule.class
})

public interface AppComponent {

    FeedComponent plus(FeedModule feedModule);
//    DetailComponent plus(DetailModule detailModule);

//    void inject(FeedActivity feedActivity);
//    void inject(DetailActivity detailActivity);
}
