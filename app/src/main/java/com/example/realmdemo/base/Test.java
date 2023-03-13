package com.example.realmdemo.base;

import androidx.annotation.NonNull;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class Test extends RealmObject {

    @PrimaryKey
    private Long uid;

    private String name;
    private int age;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @NonNull
    @Override
    public String toString() {
        return "Test { uid: " + uid
                + "  name: " + name
                + "   age: " + age;
    }
}