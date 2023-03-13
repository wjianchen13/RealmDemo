package com.example.realmdemo.model;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class User3 extends RealmObject {

    @Required
    private String name3;
    private int age3;

    public String getName3() {
        return name3;
    }

    public void setName3(String name3) {
        this.name3 = name3;
    }

    public int getAge3() {
        return age3;
    }

    public void setAge3(int age3) {
        this.age3 = age3;
    }
}