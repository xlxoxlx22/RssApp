package com.test.rssapp.ui.base;

import android.app.Application;
import android.content.Context;

import com.test.rssapp.helpers.AppPreferences;
import com.test.rssapp.network.NetworkModule;

public class App extends Application {

    private static AppComponent mComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = buildComponent();
        AppPreferences.init(get(this));
    }

    public static AppComponent getComponent(){
        return mComponent;
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    protected AppComponent buildComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule())
                .build();
    }
}
