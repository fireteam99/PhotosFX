package com.photos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PhotoCardController {
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

    public void setPhotoName(String s) {
        photoName.setText(s);
    }

    public void setImage(Image i) {
        imageView.setImage(i);
    }

    public void setCation(String s) {
        caption.setText(s);
    }
}
