package com.photos.controllers;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

import com.photos.models.*;
import com.photos.util.ButtonStyle;
import com.photos.util.CreateScene;
import com.photos.util.CreateStage;
import com.photos.util.TextStyle;
import javafx.event.ActionEvent;
import javafx.event.Event;
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

/**
 * AlbumCardController class: used to create objects that are displayed on the home page
 * @author Robert Cheng, Ray Sy
 */
public class AlbumCardController {
    private Album album;

    private HomeController homeController;

    @FXML
    private MenuButton myMenuBar;

    @FXML
    private Label albumName;

    @FXML
    private VBox vBox;

    @FXML
    private MenuItem view;

    @FXML
    private MenuItem edit;

    @FXML
    private MenuItem delete;

    @FXML
    private ImageView imageView;

    /**
     * sets image upon mouse click
     * @param e EventHandler<MouseEvent>
     */
    private void setImageViewOnClick(EventHandler<MouseEvent> e) {
        imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, e);
    }

    /**
     * allows user to view an album
     * @throws IOException
     * @throws BackingStoreException
     */
    @FXML
    public void viewAlbum() throws IOException, BackingStoreException {
        System.out.println("viewing album");

        // log this as the selected album in preferences
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("selectedAlbum", album.getId());
        userPreferences.flush();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/albumDetails.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) vBox.getScene().getWindow();
        Scene scene = CreateScene.createNormalScene(root);
        stage.setScene(scene);
        AlbumDetailsController adc = loader.getController();
        adc.setAlbum(album);
        stage.show();
    }

    /**
     * allows user to edit an album
     * @throws IOException
     */
    @FXML
    public void editAlbum() throws IOException {
        System.out.println("editing album");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/singleInputModal.fxml"));
        Parent root = loader.load();
        Stage modalStage = CreateStage.createModalStage();
        modalStage.setScene(CreateScene.createSingleInputModalScene(root));
        modalStage.show();

        SingleInputModalController simc = loader.getController();
        simc.setTitleText("Editing Album");
        simc.setMessageVisibility(false);
        simc.setInputLabelText("New Album Name");
        simc.setConfirmButtonText("Save");
        simc.setConfirmButtonStyle(ButtonStyle.CONFIRM);
        simc.setInputText(album.getName());
        simc.setConfirmButtonAction(e -> {
            AlbumList aL = new AlbumList();
            try {
                //System.out.println("Album name passed in: " + album.getName());
                aL.editAlbum(album.getId(), simc.getInputText());
                //System.out.println("input text is: " + simc.getInputText());
                simc.closeModal();
                homeController.refreshAlbumFlowPane();
            } catch (IllegalArgumentException ex) {
                simc.setMessageVisibility(true);
                simc.setMessageStyle(TextStyle.DANGER);
                simc.setMessageText(ex.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
                simc.setMessageVisibility(true);
                simc.setMessageStyle(TextStyle.DANGER);
                simc.setMessageText("Unexpected error, please try again.");
            }
        });
    }

    /**
     * delete the selected album
     * @throws IOException
     */
    @FXML
    public void deleteAlbum() throws IOException {
        System.out.println("deleting album");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/confirmationModal.fxml"));
        Parent root = loader.load();
        Stage modalStage = CreateStage.createModalStage();
        modalStage.setScene(CreateScene.createConfirmationModalScene(root));
        modalStage.show();

        ConfirmationModalController cmc = loader.getController();
        cmc.setTitleText("Delete Album");
        cmc.setMessageText("Are you sure you want to delete this album?");
        cmc.setConfirmButtonStyle(ButtonStyle.DANGER);
        cmc.setConfirmButtonAction(e -> {
            AlbumList aL = new AlbumList();
            try {
                aL.deleteAlbum(album.getId());
                cmc.closeModal();
                homeController.refreshAlbumFlowPane();
            } catch (IOException ex) {
                ex.printStackTrace();
                cmc.setMessageText("Unexpected error: could not delete album.");
            }
        });
    }

    /**
     * set album name
     * @param album
     */
    public void setAlbum(Album album) {
        this.album = album;
        albumName.setText(album.getName());

        // set the image view to first image in album if availible
        List<Picture> sortedPictures = album.getPictures().stream().sorted(Comparator.comparing(Picture::getName)).collect(Collectors.toList());
        if (!sortedPictures.isEmpty()) {
            Picture thumbnail = sortedPictures.get(0);
            imageView.setImage(new Image(thumbnail.getFile().toURI().toString()));
        }

        setImageViewOnClick(e -> {
            try {
                viewAlbum();
            } catch (IOException | BackingStoreException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * helper method to store homeController reference
     * @param homeController HomeController
     */
    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }


}
