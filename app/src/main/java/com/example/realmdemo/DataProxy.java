package com.example.realmdemo;

import android.content.Context;

import com.example.realmdemo.realm.RealmHelper;

import io.realm.Realm;

public class DataProxy {

    private static DataProxy INSTANCE;

    private RealmHelper mRealmHelper;

    public static DataProxy getInstance() {
        if (INSTANCE == null) {
            synchronized (DataProxy.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataProxy();
                }
            }
        }
        return INSTANCE;
    }

    private DataProxy() {

    }

    public void init(Context context) {
        mRealmHelper = new RealmHelper(context);
    }

    /**
     * 初始化Realm 数据库
     * @param context
     */
    public void initRealm(Context context) {
        if(mRealmHelper != null) {
            mRealmHelper.initRealm(context);
        }
    }

    /**
     * 创建数据库
     */
    public void createDatabase() {
        if(mRealmHelper != null) {
            // mRealmHelper.getRealm1();
            // mRealmHelper.getRealm2();
            // mRealmHelper.getRealm3();
            // mRealmHelper.getRealm4();
            mRealmHelper.getRealm5();
        }
    }

    /**
     * 查询数据
     */
    public void find1() {
        if(mRealmHelper != null) {
            mRealmHelper.find1();
        }
    }

    /**
     * 查询数据
     */
    public void find2() {
        if(mRealmHelper != null) {
            mRealmHelper.find2();
        }
    }

    /**
     * 查询数据
     */
    public void find3() {
        if(mRealmHelper != null) {
            mRealmHelper.find3();
        }
    }

    /**
     * 查询数据
     */
    public void find4() {
        if(mRealmHelper != null) {
            mRealmHelper.find4();
        }
    }

    /**
     * 插入数据
     */
    public void insert1() {
        if(mRealmHelper != null) {
            mRealmHelper.insert1();
        }
    }

    /**
     * 插入数据
     */
    public void insert2() {
        if(mRealmHelper != null) {
            mRealmHelper.insert2();
        }
    }

    /**
     * 插入数据
     */
    public void insert3() {
        if(mRealmHelper != null) {
            mRealmHelper.insert3();
        }
    }

    /**
     * 插入数据
     */
    public void insert4() {
        if(mRealmHelper != null) {
            mRealmHelper.insert4();
        }
    }

    /**
     * 插入数据
     */
    public void insert5() {
        if(mRealmHelper != null) {
            mRealmHelper.insert5();
        }
    }

    /**
     * 插入数据
     */
    public void insert6() {
        if(mRealmHelper != null) {
            mRealmHelper.insert6();
        }
    }

}
