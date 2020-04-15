package com.photos.controllers;

import com.photos.models.Album;
import com.photos.models.AlbumList;
import com.photos.models.Picture;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.prefs.Preferences;

public class SlideshowController {
    private Picture picture;

    private Picture leftPicture;

    private Picture rightPicture;

    private Album album;

    private List<Picture> pictures;

    private int position;

    @FXML
    StackPane container;

    @FXML
    ScrollPane imageDetailsContainer;

    @FXML
    MenuItem edit;

    @FXML
    MenuItem copy;

//    @FXML
//    MenuItem move;
//
//    @FXML
//    MenuItem delete;

    @FXML
    Label name;

    @FXML
    ImageView imageView;

    @FXML
    Label date;

    @FXML
    Label caption;

    @FXML
    Button moveLeft;

    @FXML
    public void moveLeftPicture() {
        setPicture(leftPicture);
    }

    @FXML
    Button moveRight;

    @FXML public void moveRightPicture() {
        setPicture(rightPicture);
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
        refreshSlideshow();
    }

    public void refreshSlideshow() {
        // update album
        AlbumList albumList = new AlbumList();

        // get the selected album through preferences
        Preferences userPreferences = Preferences.userRoot();
        String selectedAlbumId = userPreferences.get("selectedAlbum", "");

        if (selectedAlbumId.isEmpty()) {
            album = albumList.getAlbum(picture.getAlbum());
        } else {
            album = albumList.getAlbum(selectedAlbumId);
        }

        // fill in image info
        pictures = album.getPictures();

        if (pictures.isEmpty()) {
            // if no pictures available set the visibility to false
            imageDetailsContainer.setVisible(false);
            imageDetailsContainer.setManaged(false);
        } else {
            // set image details visibility to true
            imageDetailsContainer.setVisible(true);
            imageDetailsContainer.setManaged(true);

            // find the current position based on the image specified
            for (int i = 0; i < pictures.size(); i++) {
                if (pictures.get(i).getId().equals(picture.getId())) {
                    position = i;
                    break;
                } else if (i == pictures.size() - 1) {
                    throw new IllegalStateException("Picture is not in album...?");
                }
            }

            // set the name
            name.setText(picture.getName());

            // set the image
            imageView.setImage(new Image(picture.getFile().toURI().toString()));

            // set the date
            LocalDateTime localDateTime = picture.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            date.setText(localDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.getDefault())));

            // set the captions
            caption.setText(picture.getCaption());

            // TODO: set the tags

            // set the left picture
            if (position == 0) { // wrap around
                leftPicture = pictures.get(pictures.size() - 1);
            } else {
                leftPicture = pictures.get(position - 1);
            }

            // set the right picture
            if (position == pictures.size() - 1) { // wrap around
                rightPicture = pictures.get(0);
            } else {
                rightPicture = pictures.get(position + 1);
            }

        }

    }
}
