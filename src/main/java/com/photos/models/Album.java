package com.photos.models;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//this class stores the collection of albums for a specific user

/**
 * Album class: used to create Album objects that have a collection of Pictures
 * @author Robert Cheng, Ray Sy
 */
public class Album implements Serializable {

    private String id;
    private String name;
    private String user;

    /**
     * Album constructor
     * @param name String
     * @param user String
     */
    public Album(String name, String user){
        id = UUID.randomUUID().toString();
        this.name = name;
        this.user = user;
    }

    /**
     * gets the id of this Album object
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * gets the user that this Album belongs to
     * @return String
     */
    public String getUser() {
        return this.user;
    }

    /**
     * sets the user for this Album object
     * @param user String
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * gets the name of this Album
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of this Album
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    // convenience methods

    /**
     * gets the list of Picture objects that belong to this Album
     * @return List
     */
    public List<Picture> getPictures() {
        PictureList pictureList = new PictureList();
        List<Picture> pictures = pictureList.getPictures();
        return pictures.stream().filter(p -> p.getAlbum().equals(id)).collect(Collectors.toList());
    }

    /**
     * adds a picture to this Album
     * @param picture Picture
     * @throws IOException
     */
    public void addPicture(Picture picture) throws IOException {
        picture.setAlbum(id);
        PictureList pictureList = new PictureList();
        pictureList.addPicture(picture);
    }

}
