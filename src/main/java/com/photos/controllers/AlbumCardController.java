package com.photos.controllers;

import java.io.IOException;
import java.util.prefs.Preferences;

import com.photos.models.Album;
import com.photos.models.User;
import com.photos.models.UserList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AlbumCardController {
    private Album album;

    @FXML
    private MenuButton myMenuBar;

    @FXML
    private Label albumName;

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
    public void viewAlbum(ActionEvent event) throws IOException {
        System.out.println("viewing album");
    }

    @FXML
    public void editAlbum(ActionEvent actionEvent) throws IOException {
        System.out.println("editing album");
    }

    @FXML
    public void deleteAlbum(ActionEvent actionEvent) throws IOException {
        System.out.println("deleting album");
    }

    public void setAlbum(Album album) {
        System.out.println("setting to album: " + album.getName());
        setImageViewOnClick(e -> {
            System.out.println("redirecting to album detials");
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
