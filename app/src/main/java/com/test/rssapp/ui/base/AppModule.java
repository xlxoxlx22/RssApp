package com.test.rssapp.ui.base;


import android.app.Application;
import android.content.Context;

import com.test.rssapp.data.AppEventBus;
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
    public Application provideApplication() {
        return mApplication;
    }

    @Provides
    @ApplicationScope
    public AppEventBus provideAppEventBus(){ return new AppEventBus(); }

    @Provides
    @ApplicationScope
    public Context provideContext() {
        return mApplication;
    }
}
