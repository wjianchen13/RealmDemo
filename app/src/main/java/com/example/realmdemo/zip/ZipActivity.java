package com.example.realmdemo.zip;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.realmdemo.R;
import com.example.realmdemo.base.Test;
import com.example.realmdemo.update.CustomMigration;
import com.example.realmdemo.update.CustomMigration1;
import com.example.realmdemo.utils.LogUtils;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Realm 模拟zip保存
 */
public class ZipActivity extends AppCompatActivity {

    private Realm mRealm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zip);
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
                .schemaVersion(1)
                .migration(new CustomMigration1()) // 升级数据库
                .build();
        Realm.setDefaultConfiguration(config);
    }

    /**
     * 创建或者获取数据库
     * @param v
     */
    public void onTest1(View v) {
        mRealm = Realm.getDefaultInstance();
    }

    /**
     * 关闭数据库
     */
    public void onTest2(View v) {
        if(mRealm != null) {
            mRealm.close();
            mRealm = null;
        }
    }

    /**
     * 查询数据
     */
    public void onTest3(View v) {
        RealmResults<Test> testList = mRealm.where(Test.class).findAll();
        if(testList != null) {
            for(int i = 0; i < testList.size(); i ++) {
                Test test = testList.get(i);
                LogUtils.log("第[" + i + "] 个  name: " + test.getName() + "   age: " + test.getAge());
            }
        }
    }

    /**
     * 删除数据
     * @param v
     */
    public void onTest4(View v) {
        RealmResults<Test> testList = mRealm.where(Test.class).findAll();
        if(testList != null)
            testList.deleteAllFromRealm();
    }

    /**
     * 插入数据
     */
    public void onTest5(View v) {
        // 这里需要线程同步，否则获取的id都可能为1
        if(mRealm != null) {
            mRealm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    Test test = realm.createObject(Test.class, getPrimaryKey(realm));
                    LogUtils.log("primary id1: " + test.getUid());
                    test.setName("test111");
                    test.setAge(1);
                }
            });
        }

        final Test test = new Test();
        test.setUid(getPrimaryKey(mRealm));
        test.setName("test222");
        test.setAge(2);
        LogUtils.log("primary id2: " + test.getUid());
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(test);
            }
        });
    }

    /**
     * 插入数据
     */
    public void onTest6(View v) {
        final Test test = new Test();
        test.setUid(getPrimaryKey(mRealm));
        test.setName("test33");
        test.setAge(3);
        LogUtils.log("primary id3: " + test.getUid());
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(test);
            }
        });
    }

    /**
     * 更新数据
     */
    public void onTest7(View v) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //先查找后得到User对象
                Test t = realm.where(Test.class).findFirst();
                LogUtils.log("t: " + t.toString());
                t.setAge(100);
            }
        });
    }

    /**
     * 更新数据
     */
    public void onTest8(View v) {
        Test t = mRealm.where(Test.class).findFirst();
        Test t1 = new Test();
        t1.setAge(100);
        t.setAge(100);
    }

    private long getPrimaryKey(Realm realm) {
        long nextId = 0;
        if(realm != null) {
            Number maxId = realm.where(Test.class).max("uid");
            nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
        }
        return nextId;
    }

}