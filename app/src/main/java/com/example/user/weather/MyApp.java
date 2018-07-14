package com.example.user.weather;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by student on 14.07.18.
 */

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
