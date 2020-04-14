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

        System.out.println("Chose file: " + photoPath.toString());
    }

}
