package com.photos.models;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//this class stores the collection of albums for a specific user
public class Album implements Serializable {

    private String id;
    private String name;
    private String user;

    public Album(String name, String user){
        id = UUID.randomUUID().toString();
        this.name = name;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getUser() {
        return this.user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Picture> getPictures() {
        PictureList pictureList = new PictureList();
        List<Picture> pictures = pictureList.getPictures();
        return pictures.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
    }

    public void addPicture(Picture picture) throws IOException {
        picture.setAlbum(id);
        PictureList pictureList = new PictureList();
        pictureList.addPicture(picture);
    }

}
