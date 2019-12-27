package com.dell.actapp.utils;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();

        Logger.init();
    }

    public static Context getAppContext() {
        return App.context;
    }
}
