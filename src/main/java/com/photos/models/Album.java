package com.photos.models;

import java.io.Serializable;
import java.util.ArrayList;

//this class stores the collection of albums for a specific user
public class Album implements Serializable {

    private String albumName;
    private String user;
    private ArrayList<Picture> photos;

    public Album(String name){
        this.albumName = name;
    }

    public String getUser(){
        return this.user;
    }
    public String getAlbumName(){
        return this.albumName;
    }
    public ArrayList<Picture> getPhotos(){
        return this.photos;
    }
}
