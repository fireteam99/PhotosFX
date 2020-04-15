package com.photos.models;

import javafx.scene.image.Image;
import java.io.File;
import java.io.Serializable;
import java.util.*;

public class Picture implements Serializable {
    private String id;
    private String album;
    private String name;
    private String caption;
    private Date date;
    private Map<String, String> tags;
    private File file;

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

    public String getId() {
        return id;
    }

    // gets the id of the album associated with picture (the album it belongs to)
    public String getAlbum() {
        return album;
    }

    // sets the id of the album associated with picture (the album it belongs to)
    public void setAlbum(String album) {
        this.album = album;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    // gets the date where the file was last modified
    public Date getDate() {
        return date;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

}
