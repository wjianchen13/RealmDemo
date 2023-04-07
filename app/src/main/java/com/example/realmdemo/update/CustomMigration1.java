package com.example.realmdemo.update;

import com.example.realmdemo.utils.LogUtils;

import io.realm.DynamicRealm;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * 升级数据库
 * User1表添加一个uid字段，设置成主键
 */
public class CustomMigration1 implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        LogUtils.log("oldVersion: " + oldVersion + "   newVersion: " + newVersion);
        RealmSchema schema = realm.getSchema();
        if (oldVersion == 0 && oldVersion < newVersion) {
            RealmObjectSchema zipBean = schema.create("ZipBean");
            if(zipBean != null) {
                zipBean.addField("zid", long.class, FieldAttribute.PRIMARY_KEY);
                zipBean.addField("zipDownloadSize", long.class);
                zipBean.addField("zipAllSize", long.class);
                zipBean.addField("zipUrl", String.class);
                zipBean.addField("zipName", String.class);
                zipBean.addField("zipVersion", int.class);
            }
            oldVersion ++;
        }

    }
}