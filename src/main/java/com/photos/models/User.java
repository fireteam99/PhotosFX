package com.photos.models;

import java.io.Serializable;
import java.util.ArrayList;

/*
    When a user wants to create a new album, they must create a new array list of Picture objects first,
    then add it to their collection of albums (array list). Then this array list of albums must be
    serialized, via the AlbumsList class, to the master HashMap of albums.
 */
public class User implements Serializable{

    private String username = "";
    private String password = "";
    private ArrayList<Album> albums; //collection of albums for this user

    //constructor to create new user objects
    public User(String user, String pw) {
        this.username = user;
        this.password = pw;
        this.albums = new ArrayList<Album>();
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public ArrayList<Album> getAlbums(){
        return this.albums;
    }

}
