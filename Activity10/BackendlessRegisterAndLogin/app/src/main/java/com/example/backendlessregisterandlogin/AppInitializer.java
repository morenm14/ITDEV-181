package com.example.backendlessregisterandlogin;

import android.app.Application;

import com.backendless.Backendless;

public class AppInitializer extends Application {

    public static final String APPLICATION_ID = "0639753A-28F1-79EC-FF17-071231F2CE00";
    public static final String API_KEY = "88A97712-599A-4A7A-A194-6F11BF10FC87";

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.initApp( getApplicationContext(), APPLICATION_ID,API_KEY);
    }
}
