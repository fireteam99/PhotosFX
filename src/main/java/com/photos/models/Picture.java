package com.photos.models;

import javafx.scene.image.Image;
import java.io.File;
import java.util.*;

/**
 * Picture class: used to make Picture objects
 * @author Robert Cheng, Ray Say
 */
public class Picture {
    private String id;
    private String album;
    private String name;
    private String caption;
    private Date date;
    private Map<String, String> tags;
    private File file;

    /**
     * Picture class constructor - each picture has a unique id
     * @param album String (name of album to which this Picture object belongs)
     * @param file File (path to this image)
     */
    public Picture(String album, File file){
        id = UUID.randomUUID().toString();
        this.album = album;
        date = new Date(file.lastModified());
        name = file.getName();
        // sets default caption to the file's name plus the file's date
        caption = file.getName() + ' ' + date.toString();
        tags = new HashMap<>();
        this.file = file;
    }

    /**
     * getId gets the id of a Picture object
     * @return
     */
    public String getId() {
        return id;
    }

    /** gets the id of the album associated with picture (the album it belongs to)
     * @return String
     */
    public String getAlbum() {
        return album;
    }

    /** sets the id of the album associated with picture (the album it belongs to)
     * @param album String
     */
    public void setAlbum(String album) {
        this.album = album;
    }

    /**
     * gets the name of this Picture object
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name of this Picture object
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the caption of a Picture object
     * @return String
     */
    public String getCaption() {
        return caption;
    }

    /**
     * sets the caption of a Picture object
     * @param caption String
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**gets the date where the file was last modified
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * gets the Map of tags for this Picture object
     * @return Map
     */
    public Map<String, String> getTags() {
        return tags;
    }

    /**
     * sets the tags for a Picture with the given Map
     * @param tags Map
     */
    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    /**
     * gets the file (i.e. the image as a path) of a Picture object
     * @return File
     */
    public File getFile() {
        return file;
    }

    /**
     * sets the File of a Picture object
     * @param file File
     */
    public void setFile(File file) {
        this.file = file;
    }

}
