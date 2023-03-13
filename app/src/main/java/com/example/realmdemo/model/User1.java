package com.example.realmdemo.model;

import io.realm.RealmModel;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

@RealmClass
public class User1 implements RealmModel {

//    @PrimaryKey
//    private Long uid;
    private String name;
    private int sex;

//    public Long getUid() {
//        return uid;
//    }
//
//    public void setUid(Long uid) {
//        this.uid = uid;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}

