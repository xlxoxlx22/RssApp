package com.test.rssapp.data;
import java.util.List;
import java.util.ArrayList;
import com.google.gson.Gson;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.test.rssapp.network.model.response.Article;
import com.test.rssapp.network.model.response.ResponseObject;


public class AppPreferences {

    private SharedPreferences mSharedPrefs;
    private static final String PREFERENCE_RESPONSE_KEY = "response";


    public AppPreferences(SharedPreferences sharedPreferences) {
        this.mSharedPrefs = sharedPreferences;
    }

    public void saveArticles(ResponseObject response) {
        SharedPreferences.Editor editor = mSharedPrefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(response);
        editor.putString(PREFERENCE_RESPONSE_KEY, json);
        editor.apply();
    }

    public List<Article> getArticlesList() {
        Gson gson = new Gson();
        String json = mSharedPrefs.getString(PREFERENCE_RESPONSE_KEY, null);

        if (!TextUtils.isEmpty(json)) {
            ResponseObject obj = gson.fromJson(json, ResponseObject.class);
            return obj.getArticles();
        }
        return new ArrayList<>();
    }
}
