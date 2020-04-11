package com.photos.controllers;

import javafx.scene.image.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Picture {

    private String caption;
    private Date date;
    private HashMap<String, ArrayList<String>> tags; //string = tag, arrayList = tag values
    private Image image;
    private File picturePath;

    public Picture(String caption, String filePath){
        this.caption = caption;
        this.tags = new HashMap<String, ArrayList<String>>();
        this.picturePath = new File(filePath);
        this.image = new Image(this.picturePath.toURI().toString());
        this.date = new Date(this.picturePath.lastModified());
    }

    public String getCaption(){
        return this.caption;
    }
    public Date getDate(){
        return this.date;
    }
    public HashMap<String, ArrayList<String>> getTags(){
        return this.tags;
    }
    public File getPicturePath(){
        return this.picturePath;
    }
    public Image getImage(){
        return this.image;
    }
}
