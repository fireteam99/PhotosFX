package com.photos.controllers;

import com.photos.models.Picture;
import com.photos.models.User;
import com.photos.models.UserList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class AlbumDetailsController implements Serializable{
    @FXML
    protected HeaderController headerController;

    @FXML
    protected SidebarController sidebarController;

    @FXML
    private Button addNewPhotoButton;

    private static String user;
    private static String album;
    private static final String photoDataFile = "src/main/resources/persist/serializedPhotoFile.ser";
    private static File saveFile = new File("src/main/resources/persist/userPhotos.txt");

    public void initialize() {
        headerController.setTitle("Album Name Goes Here");
        headerController.setMenuButtonAction(e -> sidebarController.toggleVisibility());
    }

    @FXML
    public void addNewPhoto(ActionEvent actionEvent) throws IOException {
        //this.saveFile = deserialize(); //make sure to get latest file of user photo paths
        Node source = (Node) actionEvent.getSource();
        Window currStage = source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Please choose an image...");
        File photoPath = fileChooser.showOpenDialog(currStage);
        Picture photo = new Picture("", photoPath.toString());

        //----save photo locations to file; after saving photo path, serialize to file----//
        //System.out.println(photo.getPicturePath().toString());
        FileOutputStream fos = new FileOutputStream(saveFile, true);
        fos.write(photo.getPicturePath().toString().getBytes());
        fos.close();
        serializePhotoPaths(saveFile);
        //System.out.println("AlbumDetails User: " + this.user);
        UserList ul = new UserList();
        ul.getUser(user).getAlbums(album).addPhoto(photo);

        //System.out.println(user);

    }

    public void thisUser(String u, String albumName){
        UserList ul = new UserList();
        User ur = ul.getUser(u);
        this.user = ur.getUsername();
        this.album = albumName;
    }

    public void serializePhotoPaths(File photoPaths) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(photoDataFile));
        oos.writeObject(photoPaths);
    }
    public static File deserialize(){
        try {
            FileInputStream fileIn = new FileInputStream(photoDataFile);
            ObjectInputStream ois = new ObjectInputStream(fileIn);
            File f = (File) ois.readObject();
            ois.close();
            fileIn.close();
            return f;
        } catch (IOException | ClassNotFoundException i){
            System.out.println("No users exist or class is not found");
            //i.printStackTrace();
            return null;
        }
    }


}
