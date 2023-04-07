package com.example.realmdemo.zip;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.realmdemo.R;
import com.example.realmdemo.update.CustomMigration1;
import com.example.realmdemo.utils.LogUtils;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Realm 模拟zip保存
 */
public class ZipActivity extends AppCompatActivity {

    private ZipDb mZipDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip);
        mZipDb = new ZipDb();
    }

    /**
     * 创建数据库
     * @param v
     */
    public void onInit(View v) {
        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("zip.realm")  // 指定数据库的名称
//                .deleteRealmIfMigrationNeeded()  // 声明版本冲突时自动删除原数据库
//                .allowWritesOnUiThread(true)
                .schemaVersion(1)
                .migration(new CustomMigration1()) // 升级数据库
                .build();
        Realm.setDefaultConfiguration(config);
    }

    /**
     * 查找所有数据
     * @param v
     */
    public void onTest1(View v) {
        mZipDb.findAll();
    }

    /**
     * 删除所有数据
     */
    public void onTest2(View v) {
        mZipDb.deleteAll();
    }

    /**
     * 插入数据1
     */
    public void onTest3(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mZipDb.getZipBean(1);
            }
        }).start();
    }

    /**
     * 插入数据2
     * @param v
     */
    public void onTest4(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mZipDb.getZipBean(2);
            }
        }).start();
    }

    /**
     * 更新数据
     */
    public void onTest5(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mZipDb.updateData(1, 150, 200, "update_url", "测试更新", 2);
            }
        }).start();


    }

    /**
     * 更新某个数据
     */
    public void onTest6(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mZipDb.update(1, 3, "update_url3");
            }
        }).start();
    }

    /**
     * 查找某个条件数据
     */
    public void onTest7(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ZipBean bean = mZipDb.findData(1);
                if(bean != null)
                    LogUtils.log(bean.toString());
            }
        }).start();
    }

    /**
     * 关闭数据库
     */
    public void onTest8(View v) {
        mZipDb.close();
    }


}