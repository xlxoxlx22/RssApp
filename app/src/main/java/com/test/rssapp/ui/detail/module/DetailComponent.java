package com.test.rssapp.ui.detail.module;


import com.test.rssapp.di.scope.ActivityScope;
import com.test.rssapp.ui.detail.DetailFragment;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = { DetailModule.class })
public interface DetailComponent {
    void inject(DetailFragment detailFragment);
}
