package com.test.rssapp.ui.base;

import android.app.Application;
import android.content.Context;

import com.test.rssapp.helpers.AppPreferences;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppPreferences.init(get(this));
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }
}
