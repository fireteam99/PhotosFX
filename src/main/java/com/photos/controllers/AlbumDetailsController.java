package com.photos.controllers;

import com.photos.models.Album;
import com.photos.models.AlbumList;
import com.photos.models.Picture;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.*;
import java.util.List;
import java.util.prefs.Preferences;

public class AlbumDetailsController implements Serializable{
    private Album album;
    @FXML
    protected HeaderController headerController;

    @FXML
    protected SidebarController sidebarController;

    @FXML
    private Button addNewPhotoButton;

    @FXML
    private FlowPane pictureFlowPane;

    public void initialize() {
        headerController.setTitle("Album Name Goes Here");
        headerController.setMenuButtonAction(e -> sidebarController.toggleVisibility());
    }

    public void setAlbum(Album album) throws IOException {
        if (album == null) {
            Preferences userPreferences = Preferences.userRoot();
            String selectedAlbumId = userPreferences.get("selectedAlbum", "");
            this.album = new AlbumList().getAlbum(selectedAlbumId);
        } else {
            this.album = album;
        }
        refreshPictureFlowPane();
    }

    public void refreshPictureFlowPane() throws IOException {
        // remove all children from flow pane
        pictureFlowPane.getChildren().clear();

        // get list of pictures
        List<Picture> pictures = album.getPictures();
        System.out.println(pictures.toString());

        for (Picture picture: pictures) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/photoCard.fxml"));
            Parent root = loader.load();
            PictureCardController pcc = loader.getController();
            pcc.setPicture(picture);

            // add the album card to flowpane
            pictureFlowPane.getChildren().add(root);
        }

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
