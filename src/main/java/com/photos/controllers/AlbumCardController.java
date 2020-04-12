package com.photos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class AlbumCardController {
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

    public void setAlbumName(String s) {
        albumName.setText(s);
    }

    public void setThumbnail(Image i) {
        imageView.setImage(i);
    }
}
