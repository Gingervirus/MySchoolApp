package com.example.amwentzel.myschoolapp.config.util;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by Armin on 2016-06-05.
 */
public class App extends Application{
    private static Context context;
    private static App singleton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate();
        App.context = getApplicationContext();
        singleton = this;
    }

    public static Context getAppContext() {
        return App.context;
    }

    public static final String TAG = App.class
            .getSimpleName();

    public static synchronized App getInstance() {
        return singleton;
    }
}
