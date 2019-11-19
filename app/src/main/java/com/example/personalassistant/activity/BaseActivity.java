package com.example.personalassistant.activity;

import android.support.v7.app.AppCompatActivity;

import com.example.personalassistant.model.Repo;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Repo.getInstance().saveData();
    }
}
