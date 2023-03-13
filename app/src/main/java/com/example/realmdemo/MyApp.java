package com.example.realmdemo;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
//        DataProxy dataProxy = DataProxy.getInstance();
//        dataProxy.init(this);
//        dataProxy.initRealm(this);
//        dataProxy.createDatabase();
    }
}
