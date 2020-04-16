package com.photos.models;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/*
    When a user wants to create a new album, they must create a new array list of Picture objects first,
    then add it to their collection of albums (array list). Then this array list of albums must be
    serialized, via the AlbumsList class, to the master HashMap of albums.
 */

/**
 * User class implements Serializable: users are stored as User objects
 * @author Robert Cheng, Ray Sy
 */
public class User implements Serializable{

    private String id;
    private String username;
    private String password;

    //constructor to create new user objects

    /**
     * User class constructor - each user has a uniquely generated id
     * @param username String
     * @param password Password
     */
    public User(String username, String password) {
        id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }

    /**
     * getID method returns a user's id
     * @return String id
     */
    public String getId() {
        return id;
    }

    /**
     * getUsername returns a user's username
     * @return String username
     */
    public String getUsername() {
        return username;
    }

    /**
     * setUsername method sets a User object's field username
     * @param username String
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * getPassword returns a user's password
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * setPassword set's a user's password
     * @param password String
     */
    public void setPassword(String password) {
        this.password = password;
    }

    // convenience methods

    /**
     * getAlbums gets all albums belonging to this user
     * @return List of type Album
     */
    public List<Album> getAlbums(){
        AlbumList albumList = new AlbumList();
        List<Album> albums = albumList.getAlbums();
        System.out.println(albums.toString());
        return albums.stream().filter(a -> a.getUser().equals(id)).collect(Collectors.toList());
    }

    /**
     * addAlbum adds a new album to a user's album collection
     * @param album Album
     * @throws IOException
     */
    public void addAlbum(Album album) throws IOException {
        album.setUser(id);
        AlbumList albumList = new AlbumList();
        albumList.addAlbum(album);
    }

    public List<Picture> getPictures() {
        List<Album> albums = getAlbums();
        List<Picture> pictures = new ArrayList<>();
        for (Album album: albums) {
            pictures.addAll(album.getPictures());
        }
        return pictures;
    }

}
