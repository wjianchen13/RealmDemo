package com.example.realmdemo.model;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class User2 extends RealmObject {

    @Required
    private String name2;
    private int age2;

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public int getAge2() {
        return age2;
    }

    public void setAge2(int age2) {
        this.age2 = age2;
    }
}