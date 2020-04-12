package com.photos.models;

import java.io.Serializable;

public class User implements Serializable{

    private String username = "";
    private String password = "";

    //constructor to create new user objects
    public User(String user, String pw) {
        this.username = user;
        this.password = pw;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

}
