package com.photos.models;

import javafx.scene.image.Image;
import java.io.File;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

/**
 * Picture class: used to make Picture objects
 * @author Robert Cheng, Ray Say
 */

public class Picture implements Serializable {
    private String id;
    private String album;
    private String name;
    private String caption;
    private Date date;
    private Map<String, String> tags;
    private File file;

    public static Picture deepCopy(Picture picture) {
        return new Picture(picture.getAlbum(), picture.getName(), picture.getCaption(), picture.getTags(), picture.getFile());
    }

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
        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        String formattedDate = localDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.getDefault()));
        caption = String.format("%s taken on %s", file.getName(), formattedDate);
        tags = new HashMap<>();
        this.file = file;
    }

    public Picture(String album, String name, String caption, Map<String, String> tags, File file) {
        id = UUID.randomUUID().toString();
        this.album = album;
        this.name = name;
        this.caption = caption;
        this.tags = tags;
        this.file = file;
        date = new Date(file.lastModified());
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
