package com.test.rssapp.ui.base;


import android.app.Application;
import android.content.Context;

import com.test.rssapp.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    @ApplicationScope
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationScope
    public Context provideContext() {
        return mApplication;
    }
}
