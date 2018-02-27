package com.test.rssapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.test.rssapp.di.scope.ApplicationScope;
import com.test.rssapp.network.model.Article;
import com.test.rssapp.network.model.RequestResponse;

import java.util.ArrayList;
import java.util.List;

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
