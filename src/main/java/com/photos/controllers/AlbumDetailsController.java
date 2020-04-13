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
    //private static File saveFile = new File("src/main/resources/persist/userPhotos.txt");
    private static ArrayList<String> allPhotoPaths = new ArrayList<String>();

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

        if (photoPath != null) { //add photo to album and serialize if photoPath is not empty
            Picture photo = new Picture("", photoPath.toString());
            this.allPhotoPaths.add(photo.getPicturePath().toString());
            serializePhotoPaths(allPhotoPaths);
            //System.out.println("AlbumDetails User: " + this.user);
            UserList ul = new UserList();
            ul.getUser(user).getAlbums(album).addPhoto(photo);
            ul.getUser(user).updateUser();
            //System.out.println(user);
        }

    }

    public void thisUser(String u, String albumName){
        UserList ul = new UserList();
        User ur = ul.getUser(u);
        this.user = ur.getUsername();
        this.album = albumName;
    }

    public void serializePhotoPaths(ArrayList<String> paths) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(photoDataFile));
        oos.writeObject(paths);
    }
    public static ArrayList<String> deserialize(){
        ArrayList<String> p = new ArrayList<String>();
        try {
            FileInputStream fileIn = new FileInputStream(photoDataFile);
            ObjectInputStream ois = new ObjectInputStream(fileIn);
            p = (ArrayList<String>) ois.readObject();
            ois.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException i){
            System.out.println("No users exist or class is not found");
            //i.printStackTrace();
            return null;
        }
        return p;
    }


}
