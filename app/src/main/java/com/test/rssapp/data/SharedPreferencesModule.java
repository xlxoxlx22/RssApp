package com.test.rssapp.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.test.rssapp.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPreferencesModule {

    private Context mContext;

    public SharedPreferencesModule(Context context) {
        this.mContext = context;
    }

    @Provides
    @ApplicationScope
    public SharedPreferences provideSharedPreferences() {
        return mContext.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
    }

    @Provides
    @ApplicationScope
    public AppPreferences provideAppPreferences(SharedPreferences preferences){
        return new AppPreferences(preferences);
    }
}
