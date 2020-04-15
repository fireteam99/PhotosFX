package com.photos.controllers;

import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.photos.models.Album;
import com.photos.models.User;
import com.photos.models.UserList;
import com.photos.util.CreateScene;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlbumCardController {
    private Album album;

    @FXML
    private MenuButton myMenuBar;

    @FXML
    private Label albumName;

    @FXML
    private VBox vBox;

    @FXML
    private MenuItem view;

    @FXML
    private MenuItem edit;

    @FXML
    private MenuItem delete;

    @FXML
    private ImageView imageView;

    private void setImageViewOnClick(EventHandler<MouseEvent> e) {
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e);
    }

    @FXML
    public void viewAlbum() throws IOException, BackingStoreException {
        System.out.println("viewing album");

        // log this as the selected album in preferences
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("selectedAlbum", album.getId());
        userPreferences.flush();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/albumDetails.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) vBox.getScene().getWindow();
        Scene scene = CreateScene.createNormalScene(root);
        stage.setScene(scene);
        AlbumDetailsController adc = loader.getController();
        adc.setAlbum(album);
        stage.show();
    }

    @FXML
    public void editAlbum() throws IOException {
        System.out.println("editing album");
    }

    @FXML
    public void deleteAlbum() throws IOException {
        System.out.println("deleting album");
    }

    public void setAlbum(Album album) {
        this.album = album;
        albumName.setText(album.getName());
        System.out.println("setting to album: " + album.getName());

        setImageViewOnClick(e -> {
            try {
                viewAlbum();
            } catch (IOException | BackingStoreException ex) {
                ex.printStackTrace();
            }
        });
    }


//    public void setCurrentUser(String str){
//        //System.out.println("Check 1: " + str);
//        UserList ul = new UserList();
//        User u = ul.getUser(str);
//        //System.out.println("Check 2: " + u.getUsername());
//        this.user = u.getUsername();
//        //System.out.println("Check 3: " + this.user);
//    }
//    public String getUser(){
//        //System.out.println("Check 4: " + this.user);
//        return this.user;
//    }

}
