package com.example.realmdemo.update;

import com.example.realmdemo.utils.LogUtils;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

/**
 * 升级数据库
 * User1表添加一个uid字段，设置成主键
 */
public class CustomMigration implements RealmMigration {

    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        LogUtils.log("oldVersion: " + oldVersion + "   newVersion: " + newVersion);
        RealmSchema schema = realm.getSchema();
        if (oldVersion == 0 && oldVersion < newVersion) {
            RealmObjectSchema personSchema = schema.get("User1");
            personSchema
                    .addField("uid", Long.class, FieldAttribute.PRIMARY_KEY)
//                    .transform(new RealmObjectSchema.Function() {
//                        @Override
//                        public void apply(DynamicRealmObject obj) {
//                            obj.set("uid", "5"); // 为uid设置值
//                        }
//                    })
                    /*.addPrimaryKey("uid")*/;
            oldVersion ++;
        }
        if(oldVersion == 1 && oldVersion < newVersion) {
            RealmObjectSchema user2 = schema.create("User2");
            if(user2 != null) {
                user2.addField("name2", String.class, FieldAttribute.REQUIRED);
                user2.addField("age2", int.class);
            }
            oldVersion ++;
        }
        if(oldVersion == 2 && oldVersion < newVersion) {
            RealmObjectSchema user2 = schema.get("User2");
            if(user2 != null) {
                user2.addField("name3", String.class, FieldAttribute.REQUIRED);
            }
            oldVersion ++;
        }
    }
}