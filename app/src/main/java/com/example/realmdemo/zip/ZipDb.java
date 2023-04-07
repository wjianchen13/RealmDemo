package com.example.realmdemo.zip;

import android.text.TextUtils;

import com.example.realmdemo.base.Test;
import com.example.realmdemo.utils.LogUtils;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * 激活数据库
 */
public class ZipDb {

    private static final long REGISTER_ID = 1l;

    public ZipDb() {
        checkDb();
    }

    /**
     * 获取表数据
     *
     * @return
     */
    public ZipBean getZipBean(long zid) {
        Realm realm = Realm.getDefaultInstance();
        ZipBean bean = null;
        if (realm != null) {
            bean = realm.where(ZipBean.class).equalTo("zid", zid).findFirst();
            if (bean == null) {
                realm.beginTransaction();//开启事务
                ZipBean zipBean = realm.createObject(ZipBean.class, zid);
                zipBean.setZipAllSize(200);
                zipBean.setZipName("测试");
                zipBean.setZipDownloadSize(100);
                zipBean.setZipVersion(1);
                zipBean.setZipUrl("hello");
                realm.commitTransaction();//提交事务
                return zipBean;
            }
        }
        return bean;
    }

    /**
     *
     */
    public void findAll() {
        Realm realm = Realm.getDefaultInstance();
        if(realm != null) {
            RealmResults<ZipBean> testList = realm.where(ZipBean.class).findAll();
            if (testList != null) {
                for (int i = 0; i < testList.size(); i++) {
                    ZipBean test = testList.get(i);
                    LogUtils.log("第[" + i + "] 个: " + test.toString());
                }
            }
        }
    }

    /**
     * 删除所有数据
     */
    public void deleteAll() {
        Realm realm = Realm.getDefaultInstance();
        if(realm != null) {
            RealmResults<ZipBean> zipBeans = realm.where(ZipBean.class).findAll();
            if (zipBeans != null)
                zipBeans.deleteAllFromRealm();
        }
    }

    /**
     * 更新数据
     */
    public void updateData(long zid, long zipDownloadSize, long zipAllSize, String zipUrl, String zipName, int zipVersion) {
        Realm realm = Realm.getDefaultInstance();
        if(realm != null) {
            realm.beginTransaction();//开启事务
            final ZipBean zip = new ZipBean();
            zip.setZid(zid);
            zip.setZipAllSize(zipAllSize);
            zip.setZipName(zipName);
            zip.setZipDownloadSize(zipDownloadSize);
            zip.setZipVersion(zipVersion);
            zip.setZipUrl(zipUrl);
            realm.copyToRealmOrUpdate(zip);
            realm.commitTransaction();//提交事务
        }
    }

    public void update(long zid, int zipVersion, String zipUrl) {
        updateData1(zid, -1, -1, zipUrl, "", zipVersion);
    }

    /**
     * 更新数据
     * @param zid
     * @param zipDownloadSize
     * @param zipAllSize
     * @param zipUrl
     * @param zipName
     * @param zipVersion
     */
    public void updateData1(long zid, long zipDownloadSize, long zipAllSize, String zipUrl, String zipName, int zipVersion) {
        Realm realm = Realm.getDefaultInstance();
        if(realm != null) {
            realm.beginTransaction();//开启事务
            ZipBean zipBean = realm.where(ZipBean.class).equalTo("zid", zid).findFirst();
            if(zipBean != null) {
                if(zipDownloadSize != -1) {
                    zipBean.setZipDownloadSize(zipDownloadSize);
                }
                if(zipAllSize != -1) {
                    zipBean.setZipAllSize(zipAllSize);
                }
                if(!TextUtils.isEmpty(zipUrl)) {
                    zipBean.setZipUrl(zipUrl);
                }
                if(!TextUtils.isEmpty(zipName)) {
                    zipBean.setZipName(zipName);
                }
                if(zipVersion != -1) {
                    zipBean.setZipVersion(zipVersion);
                }
            } else {
                final ZipBean zip = new ZipBean();
                zip.setZid(zid);
                zip.setZipAllSize(zipAllSize);
                zip.setZipName(zipName);
                zip.setZipDownloadSize(zipDownloadSize);
                zip.setZipVersion(zipVersion);
                zip.setZipUrl(zipUrl);
                realm.copyToRealmOrUpdate(zip);
            }
            realm.commitTransaction();//提交事务
        }
    }

    /**
     * 查找zid = 1 的数据
     * @param zid
     * @return
     */
    public ZipBean findData(long zid) {
        Realm realm = Realm.getDefaultInstance();
        if(realm != null) {
            ZipBean bean = realm.where(ZipBean.class).equalTo("zid", zid).findFirst();
            return bean;
        }
        return null;
    }

    private long getPrimaryKey(Realm realm) {
        long nextId = 0;
        if(realm != null) {
            Number maxId = realm.where(Test.class).max("uid");
            nextId = (maxId == null) ? 1 : maxId.intValue() + 1;
        }
        return nextId;
    }

    /**
     * 关闭数据库
     */
    public void close() {
        Realm realm = Realm.getDefaultInstance();
        if (realm != null) {
            realm.close();
        }
    }


    /**
     * 检查数据库
     */
    private void checkDb() {
//        if (mRealm == null) {
//            mRealm = Realm.getDefaultInstance();
//        }
    }

}
