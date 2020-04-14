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
public class User implements Serializable{

    private String id;
    private String username;
    private String password;

    //constructor to create new user objects
    public User(String user, String pw) {
        id = UUID.randomUUID().toString();
        this.username = user;
        this.password = pw;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Album> getAlbums(){
        AlbumList albumList = new AlbumList();
        List<Album> albums = albumList.getAlbums();
        return albums.stream().filter(a -> a.getId().equals(id)).collect(Collectors.toList());
    }

    public void addAlbum(Album album) throws IOException {
        album.setUser(id);
        AlbumList albumList = new AlbumList();
        albumList.addAlbum(album);
    }

}
