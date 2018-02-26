package com.test.rssapp.ui.base;


import android.content.Context;
import android.support.annotation.NonNull;

import com.test.rssapp.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context mAppContext;

    public AppModule(@NonNull Context context){
        this.mAppContext = context;
    }

    @Provides
    @ApplicationScope
    Context providesContext(){
        return mAppContext;
    }
}
