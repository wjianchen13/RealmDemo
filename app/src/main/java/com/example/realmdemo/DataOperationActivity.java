package com.example.realmdemo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

/**
 * 数据库操作
 */
public class DataOperationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_operation);
    }

    /**
     * 初始化数据库
     * @param v
     */
    public void onInit(View v) {
        DataProxy dataProxy = DataProxy.getInstance();
        dataProxy.init(this);
        dataProxy.initRealm(this);
        dataProxy.createDatabase();
    }


    public void onFind1(View v) {
        DataProxy.getInstance().find1();
    }

    public void onFind2(View v) {
        DataProxy.getInstance().find2();
    }

    public void onFind3(View v) {
        DataProxy.getInstance().find3();
    }

    public void onFind4(View v) {
        DataProxy.getInstance().find4();
    }

    public void onInsert1(View v) {
        DataProxy.getInstance().insert1();
    }

    public void onInsert2(View v) {
        DataProxy.getInstance().insert2();
    }

    public void onInsert3(View v) {
        DataProxy.getInstance().insert3();
    }

    public void onInsert4(View v) {
        DataProxy.getInstance().insert4();
    }

    public void onInsert5(View v) {
        DataProxy.getInstance().insert5();
    }

    public void onInsert6(View v) {
        DataProxy.getInstance().insert6();
    }

}