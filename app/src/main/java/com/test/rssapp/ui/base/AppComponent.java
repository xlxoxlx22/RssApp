package com.test.rssapp.ui.base;

import com.test.rssapp.di.scope.ApplicationScope;
import com.test.rssapp.data.SharedPreferencesModule;
import com.test.rssapp.network.NetworkModule;
import com.test.rssapp.ui.detail.module.DetailComponent;
import com.test.rssapp.ui.detail.module.DetailModule;
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
    DetailComponent plus(DetailModule detailModule);

}
