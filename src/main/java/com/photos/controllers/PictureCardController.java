package com.photos.controllers;

import com.photos.models.*;
import com.photos.util.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;


public class PictureCardController {
    private Picture picture;

    AlbumDetailsController albumDetailsController;

    @FXML
    VBox vBox;

    @FXML
    private Label photoName;

    @FXML
    private ImageView imageView;

    @FXML
    private MenuItem view;

    @FXML
    private MenuButton menuButton;

    @FXML
    public void viewPicture() throws IOException, BackingStoreException {
        System.out.println("viewing picture");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/slideshow.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) vBox.getScene().getWindow();
        Scene scene = CreateScene.createNormalScene(root);
        stage.setScene(scene);
        SlideshowController slideshowController = loader.getController();
        slideshowController.setPicture(picture);
        stage.show();

        // set the picture in preferences
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("selectedPicture", picture.getId());
        userPreferences.flush();

    }

    @FXML
    private MenuItem edit;

    @FXML
    public void editPicture() throws IOException {
        System.out.println("editing picture");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editPicture.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) vBox.getScene().getWindow();
        Scene scene = CreateScene.createNormalScene(root);
        stage.setScene(scene);
        EditPictureController epc = loader.getController();
        epc.setPicture(picture);
        epc.setPrevScreen(ScreenHistory.ALBUM_DETAILS);
        stage.show();
    }

    @FXML
    private MenuItem copy;

    @FXML
    public void copyPicture() throws IOException {
        // determine where to copy picture by using combo modal
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/comboBoxModal.fxml"));
        Parent root = loader.load();
        Stage modalStage = CreateStage.createModalStage();
        modalStage.setScene(CreateScene.createComboBoxModalScene(root));
        modalStage.show();

        ComboBoxModalController cbmc = loader.getController();
        cbmc.setTitleText("Copy Picture");
        cbmc.setComboLabelText("Destination Album");
        cbmc.setConfirmButtonText("Copy");

        // get a list of all the availible albums
        UserList userList = new UserList();

        Preferences userPreferences = Preferences.userRoot();
        String sessionUserId = userPreferences.get("sessionUser", "");

        if (sessionUserId.isEmpty()) {
            throw new IllegalStateException("User must be logged in to view slideshow");
        }

        User user = userList.getUser(sessionUserId);
        List<Album> albums = user.getAlbums();

        List<String> albumNames = albums.stream().map(a -> a.getName()).collect(Collectors.toList());

        cbmc.setComboBoxValues(albumNames);

        cbmc.setConfirmButtonStyle(ButtonStyle.CONFIRM);

        cbmc.setMessageVisibility(false);

        // set confirm button action
        cbmc.setConfirmButtonAction(e -> {
            AlbumList albumList = new AlbumList();
            String destinationAlbumName = cbmc.getComboBoxValue();

            if (destinationAlbumName == null) {
                cbmc.setMessageVisibility(true);
                cbmc.setMessageText("Please select an album to copy to.");
                cbmc.setMessageStyle(TextStyle.DANGER);
            } else {
                Album destinationAlbum = albums.stream().filter(a -> a.getName().equals(destinationAlbumName)).findFirst().orElse(null);;
                if (destinationAlbum == null) {
                    throw new IllegalStateException("what just happened???");
                }

                // duplicate the picture
                Picture duplicatePicture = Picture.deepCopy(picture);

                // add it to selected album
                try {
                    destinationAlbum.addPicture(duplicatePicture);
                } catch (IOException ex) {
                    // TODO: make useful
                    ex.printStackTrace();
                }

                // refresh flowpane
                try {
                    albumDetailsController.refreshPictureFlowPane();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // close out of the modal
                cbmc.closeModal();
            }

        });

    }

    @FXML
    private MenuItem move;

    @FXML
    public void movePicture() throws IOException {
        // determine where to copy picture by using combo modal
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/comboBoxModal.fxml"));
        Parent root = loader.load();
        Stage modalStage = CreateStage.createModalStage();
        modalStage.setScene(CreateScene.createComboBoxModalScene(root));
        modalStage.show();

        ComboBoxModalController cbmc = loader.getController();
        cbmc.setTitleText("Move Picture");
        cbmc.setComboLabelText("Destination Album");
        cbmc.setConfirmButtonText("Move");
        // get a list of all the availible albums
        UserList userList = new UserList();

        Preferences userPreferences = Preferences.userRoot();
        String sessionUserId = userPreferences.get("sessionUser", "");

        if (sessionUserId.isEmpty()) {
            throw new IllegalStateException("User must be logged in to view slideshow");
        }

        User user = userList.getUser(sessionUserId);
        List<Album> albums = user.getAlbums();

        List<String> albumNames = albums.stream().map(a -> a.getName()).collect(Collectors.toList());

        cbmc.setComboBoxValues(albumNames);

        cbmc.setConfirmButtonStyle(ButtonStyle.CONFIRM);

        cbmc.setMessageVisibility(false);

        // set confirm button action
        cbmc.setConfirmButtonAction(e -> {
            AlbumList albumList = new AlbumList();
            String destinationAlbumName = cbmc.getComboBoxValue();

            if (destinationAlbumName == null) {
                cbmc.setMessageVisibility(true);
                cbmc.setMessageText("Please select an album to copy to.");
                cbmc.setMessageStyle(TextStyle.DANGER);
            } else {
                Album destinationAlbum = albums.stream().filter(a -> a.getName().equals(destinationAlbumName)).findFirst().orElse(null);;
                if (destinationAlbum == null) {
                    throw new IllegalStateException("what just happened???");
                }

                // update the album id of the picture
                PictureList pictureList = new PictureList();
                try {
                    pictureList.editPictureAlbum(picture.getId(), destinationAlbum.getId());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // refresh flow pane
                try {
                    albumDetailsController.refreshPictureFlowPane();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // close out of the modal
                cbmc.closeModal();
            }

        });

    }

    @FXML
    private MenuItem delete;

    @FXML
    public void deletePicture() {
        // open up confirmation modal
        FXMLLoader modalLoader = new FXMLLoader(getClass().getResource("/fxml/confirmationModal.fxml"));
        try {
            Parent modalRoot = modalLoader.load();
            Stage modalStage = CreateStage.createModalStage();
            modalStage.setScene(CreateScene.createConfirmationModalScene(modalRoot));
            modalStage.show();

            ConfirmationModalController cmc = modalLoader.getController();
            cmc.setTitleText("Delete Picture");
            cmc.setMessageText("Are you sure you want to delete " + picture.getName() + "?");
            cmc.setConfirmButtonStyle(ButtonStyle.DANGER);
            cmc.setConfirmButtonAction(event -> {
                try {
                    // delete the picture
                    new PictureList().deletePicture(picture.getId());
                    // close the modal
                    cmc.closeModal();
                    // refresh the flow pane
                    albumDetailsController.refreshPictureFlowPane();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (IOException ex) {
            // TODO: Show a modal on why it failed
            ex.printStackTrace();
        }

    }

    @FXML
    private Label caption;

    public void setAlbumDetailsController(AlbumDetailsController albumDetailsController) {
        this.albumDetailsController = albumDetailsController;
    }

    private void setImageViewOnClick(EventHandler<MouseEvent> e) {
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e);
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
        photoName.setText(picture.getName());
        caption.setText(picture.getCaption());
        System.out.println("Setting picture to be: " + picture.getName());
        setImageViewOnClick(e -> {
            System.out.println("Redirecting to slideshow");
            try {
                viewPicture();
            } catch (IOException | BackingStoreException ex) {
                // TODO: change to usesful info
                ex.printStackTrace();
            }
        });
        imageView.setImage(new Image(picture.getFile().toURI().toString()));

        System.out.println(picture.getFile().getPath());

    }

    public void setMenuButtonDisable(boolean b) {
        menuButton.setDisable(b);
    }

    public void setViewDisable(boolean b) {
        view.setDisable(b);
    }

    public void setMoveDisable(boolean b) {
        move.setDisable(b);
    }

    public void setDeleteDisable(boolean b) {
        delete.setDisable(b);
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
