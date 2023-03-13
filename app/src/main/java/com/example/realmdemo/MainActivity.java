package com.example.realmdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.realmdemo.base.BaseActivity;

/**
 * Realm数据库
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBase(View v) {
        startActivity(new Intent(this, BaseActivity.class));
    }

    public void onOperation(View v) {
        startActivity(new Intent(this, DataOperationActivity.class));
    }

}