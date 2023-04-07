package com.example.realmdemo.zip;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * 激活数据库
 */
public class ZipDb {

    private static final long REGISTER_ID = 1l;
    private Realm mRealm;

    public ZipDb() {
        checkDb();
    }

    /**
     * 获取注册表数据
     *
     * @return
     */
    public ZipBean getRegisterBean() {
        checkDb();
        ZipBean bean = null;
//        if (mRealm != null) {
//            bean = mRealm.where(ZipBean.class).findFirst();
//        }
        return new ZipBean(bean);
    }

//    /**
//     * 更新apk激活标志
//     */
//    public void updateApkUpLoadOK(int upload) {
//        checkDb();
//        if (mRealm != null) {
//            mRealm.executeTransactionAsync(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                    ZipBean bean = findFirst(realm);
//                    if (bean == null) {
//                        bean = new ZipBean();
//                        bean.setApkUpLoadOK(upload);
//                        if (realm != null)
//                            realm.copyToRealmOrUpdate(bean);
//                    } else {
//                        bean.setApkUpLoadOK(upload);
//                    }
//                }
//            });
//        }
//    }
//
//    /**
//     * 保存激活状态
//     *
//     * @param status
//     */
//    public void updateRegisterStatus(int status) {
//        checkDb();
//        if (mRealm != null) {
//            mRealm.executeTransactionAsync(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                    ZipBean bean = findFirst(realm);
//                    if (bean == null) {
//                        bean = new ZipBean();
//                        bean.setRegisterState(status);
//                        if (realm != null)
//                            realm.copyToRealmOrUpdate(bean);
//                    } else {
//                        bean.setRegisterState(status);
//                    }
//                }
//            });
//        }
//    }
//
//    /**
//     * 是否已经注册成功
//     *
//     * @return
//     */
//    public boolean isRegisterCompleted() {
//        ZipBean bean = getRegisterBean();
//        if (bean != null && (bean.getRegisterState() == 2 || bean.getApkUpLoadOK() == 1)) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 获取激活状态
//     * 这里面的数据只能读，不能写
//     * @return
//     */
//    public int getRegisterState() {
//        ZipBean bean = getRegisterBean();
//        if (bean != null) {
//            return bean.getRegisterState();
//        }
//        return -10;
//    }
//
//    /**
//     * 查找数据
//     */
//    private ZipBean findFirst(Realm realm) {
//        return realm != null ? realm.where(ZipBean.class).equalTo("id", REGISTER_ID).findFirst() : null;
//    }
//
//    /**
//     * 更新数据
//     *
//     * @param bean
//     */
//    public void insertOrUpdate(ZipBean bean) {
//        checkDb();
//        if (mRealm != null && bean != null) {
//            mRealm.executeTransactionAsync(new Realm.Transaction() {
//                @Override
//                public void execute(Realm realm) {
//                    realm.copyToRealmOrUpdate(bean);
//                }
//            });
//        }
//    }
//
//    /**
//     * 关闭数据库
//     */
//    public void close() {
//        if (mRealm != null) {
//            mRealm.close();
//            mRealm = null;
//        }
//    }
//
//    /**
//     * 查询所有数据
//     */
//    public void testFind() {
//        RealmResults<ZipBean> beans = mRealm.where(ZipBean.class).findAll();
//        if (beans != null) {
//            for (int i = 0; i < beans.size(); i++) {
//                ZipBean test = beans.get(i);
//                LogUtils.i(AppRegister.TAG, "===============================> 第[" + i + "] 个  id: " + test.getId()
//                        + "   registerState: " + test.getRegisterState() + "   apkUpLoadOK: " + test.getApkUpLoadOK());
//            }
//        }
//    }

    /**
     * 检查数据库
     */
    private void checkDb() {
        if (mRealm == null) {
            mRealm = Realm.getDefaultInstance();
        }
//        if (mRealm != null) {
//            RealmResults<ZipBean> userList = mRealm.where(ZipBean.class).findAll();
//            if (userList != null && !userList.isEmpty() && userList.size() > 1) {
//                userList.deleteAllFromRealm();
//            }
//        }
    }

}
