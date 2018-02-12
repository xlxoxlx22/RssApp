package com.test.rssapp.ui.base;

import android.app.Application;

import com.test.rssapp.helpers.AppPreferences;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppPreferences.init(this.getApplicationContext());
    }
}
