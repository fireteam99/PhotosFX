package com.photos.controllers;

import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AlbumCardController {
    private String user;

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

    @FXML
    public void viewAlbum(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/albumDetails.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        Scene scene = new Scene(root, 750, 500);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void editAlbum(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editAlbum.fxml"));
        //----send the album object to EditAlbumController----//
//        EditAlbumController eactr = loader.getController();
//        eactr.currUser(user,albumName.toString());
        //
        Parent root = loader.load();
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        Scene scene = new Scene(root, 750, 500);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteAlbum(ActionEvent actionEvent) {
    }

    public void setAlbumName(String s) {
        albumName.setText(s);
    }

    public void setThumbnail(Image i) {
        imageView.setImage(i);
    }

    public void currentUser(String u){
        this.user = u;
    }

}
