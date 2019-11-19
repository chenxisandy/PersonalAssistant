package com.example.personalassistant;

import android.app.Application;

import com.example.personalassistant.model.Repo;

import org.litepal.LitePal;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
        Repo.getInstance().initData();
    }
}
