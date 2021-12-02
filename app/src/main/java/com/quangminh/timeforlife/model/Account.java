package com.quangminh.timeforlife.model;

public class Account {
    String email;
    String pass;

    String id;



    public Account(String email, String pass, String id) {
        this.email = email;
        this.pass = pass;
        this.id = id;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
