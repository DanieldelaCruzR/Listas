package com.example.danni.listas.model;

/**
 * Created by danni on 12/10/2017.
 */

public class User {
    private String name,email,uid,phone;


    public User() {
    }

    public User(String uid, String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.uid = uid;
        this.phone = phone;

    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getUid() {
        return uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
