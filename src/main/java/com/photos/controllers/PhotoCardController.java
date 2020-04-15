package com.photos.controllers;

import com.photos.models.Picture;
import com.photos.util.CreateScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class PhotoCardController {
    private Picture picture;
    @FXML
    private Label photoName;

    @FXML
    private ImageView imageView;

    @FXML
    private MenuItem view;

    @FXML
    private MenuItem edit;

    @FXML
    private MenuItem copy;

    @FXML
    private MenuItem move;

    @FXML
    private MenuItem delete;

    @FXML
    private Label caption;

    @FXML
    public void viewPicture(ActionEvent event) throws IOException {
        System.out.println("viewing picture");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editPicture.fxml"));
        Parent root = loader.load();
        Node n = (Node) event.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        Scene scene = CreateScene.createNormalScene(root);
        stage.setScene(scene);
        EditPictureController epc = loader.getController();
        epc.setPicture(picture);
        stage.show();
    }

    @FXML
    public void editPicture() {
        System.out.println("editing picture");
    }

    @FXML
    public void copyPicture() {
        System.out.println("copying picture");

    }

    @FXML
    public void movePicture() {
        System.out.println("moving picture");

    }

    @FXML
    public void deletePicture() {
        System.out.println("deleting picture");

    }

    private void setImageViewOnClick(EventHandler<MouseEvent> e) {
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e);
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
        System.out.println("Setting picture to be: " + picture.getName());
        setImageViewOnClick(e -> {
            System.out.println("Redirecting to slideshow");
        });
        imageView.setImage(new Image(picture.getFile().toURI().toString()));

        System.out.println(picture.getFile().getPath());

    }

    public void setPhotoName(String s) {
        photoName.setText(s);
    }

    public void setThumbnail(Image i) {
        imageView.setImage(i);
    }

    public void setCaption(String s) {
        caption.setText(s);
    }
}
