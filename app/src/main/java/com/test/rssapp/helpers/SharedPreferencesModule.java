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

import dagger.Provides;

public class SharedPreferencesModule {

    private static final String PREFERENCE_RESPONSE_KEY = "response";


    private Context mContext;
    private static SharedPreferencesModule instance;
    private SharedPreferences mSharedPrefs;


    public SharedPreferencesModule(Context context) {
        this.mContext = context;
//        this.mSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    @Provides
    @ApplicationScope
    SharedPreferences provideSharedPreferences() {
        return mContext.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE);
    }


    public void saveArticles(RequestResponse response) {
        SharedPreferences.Editor editor = mSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(response);
        editor.putString(PREFERENCE_RESPONSE_KEY, json);
        editor.apply();

    }

    public List<Article> getArticlesList() {
        Gson gson = new Gson();
        String json = mSharedPrefs.getString(PREFERENCE_RESPONSE_KEY, null);

        if (json != null) {
            RequestResponse obj = gson.fromJson(json, RequestResponse.class);
            return obj.getArticles();
        }

        return new ArrayList<Article>();
    }
}
