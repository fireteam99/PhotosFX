package com.photos.controllers;

import com.photos.models.Picture;
import com.photos.models.PictureList;
import com.photos.util.CreateScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class EditPictureController {
    private Picture picture;

    private Map<String, String> tags;

    private File file;

    private Stage previousStage;

    @FXML
    HeaderController headerController;

    @FXML
    SidebarController sidebarController;

    @FXML
    HBox sidebarHBox;

    @FXML
    StackPane mainStackPane;

    @FXML
    TextField nameInput;

    @FXML
    TextArea captionInput;

    @FXML
    FlowPane flowpane;

    @FXML
    ImageView imageView;

    @FXML
    Label filePath;

    @FXML
    Button editFilePath;

    @FXML
    Button cancelButton;

    @FXML
    Button saveButton;

    @FXML
    public void savePicture() throws IOException {
        PictureList pictureList = new PictureList();
        pictureList.editPicture(picture.getId(), picture.getAlbum(), nameInput.getText(), captionInput.getText(), tags, file);
    }


    public void exitScreen(ActionEvent event) throws IOException {
        if (previousStage != null) {
            previousStage.show();
        } else {
            // default back to home page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            Parent root = loader.load();
            Node n = (Node) event.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            Scene scene = CreateScene.createNormalScene(root);
            stage.setScene(scene);
            stage.show();
        }
    }


    public void initialize() {
        // deals with sidebar blocking actions
        mainStackPane.setPickOnBounds(false);
        sidebarHBox.setPickOnBounds(false);
        headerController.setTitle("Home");
        headerController.setMenuButtonAction(e -> sidebarController.toggleVisibility());
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
        nameInput.setText(picture.getName());
        captionInput.setText(picture.getCaption());

        // TODO: set the tags

        imageView.setImage(new Image(picture.getFile().getPath()));

        filePath.setText(picture.getFile().getPath());

        tags = picture.getTags();

        file = picture.getFile();
    }

    @FXML
    public void exit() {
        System.out.println("Cancel");
    }

    @FXML
    public void saveChanges() {
        System.out.println("Save");
    }
}
