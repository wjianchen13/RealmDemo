package com.example.realmdemo.realm;

import android.content.Context;

import com.example.realmdemo.model.User;
import com.example.realmdemo.model.User1;
import com.example.realmdemo.update.CustomMigration;
import com.example.realmdemo.utils.LogUtils;

import java.security.SecureRandom;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.exceptions.RealmMigrationNeededException;

public class RealmHelper {

    public static final String DB_NAME = "myRealm.realm";
    private Context mContext = null;
    private Realm mRealm;

    public RealmHelper(Context context) {

    }

    /**
     * 初始化Realm 数据库
     * @param context
     */
    public void initRealm(Context context) {
        if(context != null)
            Realm.init(context); // 初始化realm
    }

    /**
     * 创建数据库方式1
     * @return
     */
    public void getRealm1() {
        mRealm = Realm.getDefaultInstance();
    }

    /**
     * 创建数据库方式2
     * @return
     */
    public void getRealm2() {
        //这时候会创建一个叫做 default.realm的Realm文件，一般来说，
        // 这个文件位于/data/data/包名/files/。通过realm.getPath()来获得该Realm的绝对路径
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(DB_NAME) //文件名
                .schemaVersion(3) //版本号
//                .allowWritesOnUiThread(true) // 允许在主线程插入数据
                .migration(new CustomMigration()) // 升级数据库
                .build();
//        mRealm = Realm.getInstance(config);

        // 如果数据库升级出现异常，则删除旧的数据库，重新创建一个
        try {
            mRealm = Realm.getInstance(config);
        } catch (RealmMigrationNeededException e){
            try {
                Realm.deleteRealm(config);
                //Realm file has been deleted.
                mRealm =  Realm.getInstance(config);
            } catch (Exception ex){
                throw ex;
                //No Realm file to remove.
            }
        }

    }

    /**
     * 创建数据库方式3
     * @return
     */
    public void getRealm3() {
        //创建保存在内存中的realm，应用关闭后就清除了
        RealmConfiguration myConfig = new RealmConfiguration.Builder()
                .name("myrealm.realm")
                .inMemory()
                .build();
        mRealm = Realm.getInstance(myConfig);
    }

    /**
     * 创建数据库方式4
     * @return
     */
    public void getRealm4() {
        // 创建加密的realm
        byte[] key = new byte[64];
        new SecureRandom().nextBytes(key);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .encryptionKey(key)
                .build();
        mRealm = Realm.getInstance(config);
    }

    /**
     * 创建数据库方式2
     * @return
     */
    public void getRealm5() {
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name(DB_NAME) //文件名
                .schemaVersion(1) //版本号
//                .deleteRealmIfMigrationNeeded() //声明版本冲突时自动删除原数据库
                .migration(new CustomMigration()) // 升级数据库
                .build();

        // 如果数据库升级出现异常，则删除旧的数据库，重新创建一个
        try {
            mRealm = Realm.getInstance(config);
            LogUtils.log("version: " + mRealm.getConfiguration().getSchemaVersion());
//            throw new RealmMigrationNeededException("", ""); // 模拟获取数据库异常，重新创建
        } catch (RealmMigrationNeededException e){
            LogUtils.log("RealmMigrationNeededException: ");
            try {
                Realm.deleteRealm(config);
                //Realm file has been deleted.
                mRealm =  Realm.getInstance(config);
            } catch (Exception ex){
                LogUtils.log("Exception: ");
//                throw ex;
                //No Realm file to remove.
            }
        }

    }

    /**
     * 关闭数据库
     */
    public void closeRealm() {
        if(mRealm != null) {
            mRealm.close();
            mRealm = null;
        }
    }

    /**
     * 查询
     * 可能重复的记录不会输出，所以这里需要把每条记录内容改成不一样
     */
    public void find1() {
        RealmResults<User> userList = mRealm.where(User.class).findAll();
        if(userList != null) {
            for(int i = 0; i < userList.size(); i ++) {
                User user = userList.get(i);
                LogUtils.log("第[" + i + "] 个  name: " + user.getName() + "   age: " + user.getAge());
            }
        }
    }

    /**
     * 异步查询
     */
    public void find2() {
        RealmResults<User> userList = mRealm.where(User.class)
                .equalTo("name", "Gavin")
                .findAllAsync();
        if(userList != null) {
            for(int i = 0; i < userList.size(); i ++) {
                User user = userList.get(i);
                LogUtils.log("第[" + i + "] 个  name: " + user.getName() + "   age: " + user.getAge());
            }
        }
    }

    /**
     * 查询
     */
    public void find3() {
        User user = mRealm.where(User.class).findFirst();
        if(user != null) {
            LogUtils.log("user  name: " + user.getName() + "   age: " + user.getAge());
        }
    }

    /**
     * 查询
     */
    public void find4() {

    }

    /**
     * 开始事务 插入数据
     * UI线程不允许事务操作，需要设置 allowWritesOnUiThread(true)才可以
     */
    public void insert1() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class);
                user.setName("Gavin");
                user.setAge(23);
            }
        });
    }

    /**
     * 开始事务 插入数据
     * 这种方式反而不设置allowWritesOnUiThread也不会报错
     */
    public void insert2() {
        mRealm.beginTransaction();//开启事务
        User user = mRealm.createObject(User.class);
        user.setName("Gavin");
        user.setAge(30);
        mRealm.commitTransaction();//提交事务
    }

    /**
     * 开始事务 插入数据
     */
    public void insert3() {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class);
                user.setName("Gavin111");
                user.setAge(222);
            }
        });
    }

    /**
     * copyToRealmOrUpdate 插入数据
     * 当model存在主键的时候，推荐使用copyToRealmOrUpdate方法插入数据。如果对象存在，就更新该对象；反之，它会创建一个新的对象
     */
    public void insert4() {
//        final User1 user = new User1();
//        user.setName("Jack");
////        user.setUid(1);
//        mRealm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.copyToRealmOrUpdate(user);
//            }
//        });
    }

    /**
     * copyToRealmOrUpdate 插入数据
     * 当model存在主键的时候，推荐使用copyToRealmOrUpdate方法插入数据。如果对象存在，就更新该对象；反之，它会创建一个新的对象
     */
    public void insert5() {
//        final User1 user = new User1();
//        user.setName("test1");
//        user.setSex(1);
//        mRealm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.copyToRealm(user);
//            }
//        });
    }


    /**
     * executeTransactionAsync方法监听
     */
    public void insert6() {
        RealmAsyncTask transaction =  mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = realm.createObject(User.class);
                user.setName("Eric");
                user.setAge(30);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                LogUtils.log("executeTransactionAsync onSuccess");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                LogUtils.log("executeTransactionAsync onError");
            }
        });
    }



}
