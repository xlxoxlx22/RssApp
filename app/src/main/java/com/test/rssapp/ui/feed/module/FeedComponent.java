package com.test.rssapp.ui.feed.module;

import com.test.rssapp.di.scope.ActivityScope;
import com.test.rssapp.ui.feed.FeedActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = { FeedModule.class })
public interface FeedComponent {
    void inject(FeedActivity feedActivity);
}
