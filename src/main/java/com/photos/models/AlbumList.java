package com.photos.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class AlbumList implements Serializable {
    static final long serialVersionUID = 1L;

    private List<Album> albumList = new ArrayList<Album>();
    public final String dataFile = "src/main/resources/persist/serializedAlbums.ser";

    //serialize list of albums
    public void serialize() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile));
        oos.writeObject(albumList);
    }

    private void init() {
        try {
            FileInputStream fileIn = new FileInputStream(dataFile);
            ObjectInputStream ois = new ObjectInputStream(fileIn);
            albumList = (ArrayList<Album>) ois.readObject();
            ois.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i){
            System.out.println("No albums exist or class is not found");
            albumList = new ArrayList<Album>();
        }
    }

    public void addAlbum(Album album) throws IOException {
        init();
        // enforce that album names are unique per user
        List<Album> filtered = albumList.stream().filter(a -> a.getName().equals(album.getName()) && a.getUser().equals(album.getUser())).collect(Collectors.toList());
        if (!filtered.isEmpty()) {
            throw new IllegalArgumentException("Album name must be unique per user.");
        }
        albumList.add(album);
        serialize();
    }

    public Album getAlbum(String id) {
        init();
        List<Album> filtered = albumList.stream().filter(a -> a.getId().equals(id)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new NoSuchElementException("Album does not exist.");
        }
        return filtered.get(0);
    }


    public void editAlbum(String id, String name) throws IOException {
        init();
        List<Album> filtered = albumList.stream().filter(a -> a.getId().equals(id)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new NoSuchElementException("Album does not exist.");
        }
        Album album = filtered.get(0);
        album.setName(name);
        serialize();
    }

    public void deleteAlbum(String id) throws IOException {
        init();
        List<Album> filtered = albumList.stream().filter(a -> a.getId().equals(id)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new NoSuchElementException("The album to delete does not exist.");
        }
        albumList = albumList.stream().filter(p -> !p.getId().equals(id)).collect(Collectors.toList());
        serialize();
    }

    public List<Album> getAlbums() {
        return albumList;
    }

}
