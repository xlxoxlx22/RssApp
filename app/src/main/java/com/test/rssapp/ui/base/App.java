package com.test.rssapp.ui.base;

import android.app.Application;
import android.content.Context;
import com.test.rssapp.data.SharedPreferencesModule;

public class App extends Application {

    private static AppComponent mComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mComponent = buildComponent();
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
                .sharedPreferencesModule(new SharedPreferencesModule(this))
                .build();
    }
}
