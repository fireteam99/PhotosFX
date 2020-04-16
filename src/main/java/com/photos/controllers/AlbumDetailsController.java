package com.photos.controllers;

import com.photos.models.Album;
import com.photos.models.AlbumList;
import com.photos.models.Picture;
import com.photos.models.PictureList;
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
import java.util.Comparator;
import java.util.List;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

/**
 * AlbumDetailsController gets the details about a selected Album object
 * @author Robert Cheng, Ray Sy
 */
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
        headerController.setTitle("Album Name");
        headerController.setMenuButtonAction(e -> sidebarController.toggleVisibility());
    }

    /**
     * sets the album
     * @param album Album
     * @throws IOException
     */
    public void setAlbum(Album album) throws IOException {
        if (album == null) {
            Preferences userPreferences = Preferences.userRoot();
            String selectedAlbumId = userPreferences.get("selectedAlbum", "");
            this.album = new AlbumList().getAlbum(selectedAlbumId);
        } else {
            this.album = album;
        }

        headerController.setTitle(album.getName());
        refreshPictureFlowPane();
    }

    /**
     * refreshes the picture objects to reflect any recent changes
     * @throws IOException
     */
    public void refreshPictureFlowPane() throws IOException {
        // remove all children from flow pane
        pictureFlowPane.getChildren().clear();

        // get list of pictures
        List<Picture> pictures = album.getPictures();
        System.out.println(pictures.toString());

        // sort pictures by name
        List<Picture> sortedPictures = pictures.stream().sorted(Comparator.comparing(Picture::getName)).collect(Collectors.toList());

        for (Picture picture: sortedPictures) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/photoCard.fxml"));
            Parent root = loader.load();
            PictureCardController pcc = loader.getController();
            pcc.setPicture(picture);
            pcc.setAlbumDetailsController(this);

            // add the album card to flowpane
            pictureFlowPane.getChildren().add(root);
        }

    }

    /**
     * add a new picture object to this album
     * @param actionEvent ActionEvent
     * @throws IOException
     */
    @FXML
    public void addNewPhoto(ActionEvent actionEvent) throws IOException {
        Node source = (Node) actionEvent.getSource();
        Window currStage = source.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
            "Image Files", "*.jpg", "*.jpeg", "*.png", "*.gif");
        fileChooser.getExtensionFilters().add(imageFilter);
        fileChooser.setTitle("Please choose an image...");

        File photoPath = fileChooser.showOpenDialog(currStage);

        if (photoPath != null) {
            // create a new photo and and save it if they chose something
            album.addPicture(new Picture(album.getId(), photoPath));

            refreshPictureFlowPane();
        }

    }

}
