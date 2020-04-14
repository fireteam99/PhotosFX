package com.photos.models;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class PictureList {
    static final long serialVersionUID = 1L;

    private List<Picture> pictureList = new ArrayList<Picture>();
    public final String dataFile = "src/main/resources/persist/serializedPictures.ser";

    //serialize list of pictures
    public void serialize() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile));
        oos.writeObject(pictureList);
    }

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

    public void addPicture(Picture picture) throws IOException {
        init();
        pictureList.add(picture);
        serialize();
    }

    public Picture getPicture(String id) {
        init();
        List<Picture> filtered = pictureList.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new NoSuchElementException("Picture does not exist.");
        }
        return filtered.get(0);
    }

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

    public void deletePicture(String id) throws IOException {
        init();
        List<Picture> filtered = pictureList.stream().filter(p -> p.getId().equals(id)).collect(Collectors.toList());
        if (filtered.isEmpty()) {
            throw new NoSuchElementException("The picture to delete does not exist.");
        }
        pictureList = pictureList.stream().filter(p -> !p.getId().equals(id)).collect(Collectors.toList());
        serialize();
    }

    public List<Picture> getPictures() {
        init();
        return pictureList;
    }

}
