package com.photos.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * PictureList class stores a collection of all pictures across users.
 * Any changes to the pictureList are serialized.
 * @author Robert Cheng, Ray Sy
 */
public class PictureList implements Serializable {
    static final long serialVersionUID = 1L;

    private List<Picture> pictureList = new ArrayList<Picture>();
    public final String dataFile = "src/main/resources/persist/serializedPictures.ser";

    //serialize list of pictures

    /**
     * serialize method serializes the master list of photos
     * @throws IOException
     */
    public void serialize() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile));
        oos.writeObject(pictureList);
    }

    /**
     * PictureList init method - deserializes from .ser file, getting the latest version of pictureList
     */
    private void init() {
        try {
            FileInputStream fileIn = new FileInputStream(dataFile);
            ObjectInputStream ois = new ObjectInputStream(fileIn);
            pictureList = (ArrayList<Picture>) ois.readObject();
            ois.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i){
            System.out.println("No pictures exist or class is not found");
            pictureList = new ArrayList<>();
        }
    }

    /**
     * addPicture adds a picture object to the pictureList
     * @param picture
     * @throws IOException
     */
    public void addPicture(Picture picture) throws IOException {
        init();
        pictureList.add(picture);
        serialize();
    }

    /**
     * getPicture gets the picture object with the specified picture id
     * @param id String
     * @return Picture object
     */
    public Picture getPicture(String id) {
        init();
        List<Picture> filtered = pictureList.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new NoSuchElementException("Picture does not exist.");
        }
        return filtered.get(0);
    }

    /**
     * editPicture method allows edits to be made to a picture object with the specified id
     * @param id String
     * @param album Album
     * @param name String
     * @param caption String
     * @param tags Map
     * @param file File
     * @throws IOException
     */
    public void editPicture(String id, String album, String name, String caption, Map<String, String> tags, File file) throws IOException {
        init();
        List<Picture> filtered = pictureList.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new NoSuchElementException("Picture does not exist.");
        }
        Picture picture = filtered.get(0);
        picture.setAlbum(album);
        picture.setName(name);
        picture.setCaption(caption);
        picture.setTags(tags);
        picture.setFile(file);
        serialize();
    }

    public void editPictureTags(String id, Map<String, String> tags) throws IOException {
        init();
        List<Picture> filtered = pictureList.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new NoSuchElementException("Picture does not exist.");
        }
        Picture picture = filtered.get(0);
        picture.setTags(tags);
        serialize();
    }

    public void editPictureAlbum(String id, String album) throws IOException {
        init();
        List<Picture> filtered = pictureList.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new NoSuchElementException("Picture does not exist.");
        }
        Picture picture = filtered.get(0);
        picture.setAlbum(album);
        serialize();
    }

    /**
     * deletePicture deletes (from file) the Picture object with the specified id
     * @param id String
     * @throws IOException
     */
    public void deletePicture(String id) throws IOException {
        init();
        List<Picture> filtered = pictureList.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new NoSuchElementException("The picture to delete does not exist.");
        }
        pictureList = pictureList.stream().filter(p -> !p.getId().equals(id)).collect(Collectors.toList());
        serialize();
    }

    /**
     * getPictures returns the master pictureList
     * @return List
     */
    public List<Picture> getPictures() {
        init();
        return pictureList;
    }

}
