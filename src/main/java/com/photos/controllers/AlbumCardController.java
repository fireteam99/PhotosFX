package com.photos.controllers;

import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.photos.models.Album;
import com.photos.models.AlbumList;
import com.photos.models.User;
import com.photos.models.UserList;
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
        modalStage.setScene(CreateScene.createDoubleInputModalScene(root));
        modalStage.show();

        SingleInputModalController simc = loader.getController();
        simc.setTitleText("Editing Album");
        simc.setMessageVisibility(false);
        simc.setInputLabelText("New Album Name");
        simc.setConfirmButtonText("Submit Edits");
        simc.setConfirmButtonStyle(ButtonStyle.CONFIRM);
        simc.setConfirmButtonAction(e -> {
            AlbumList aL = new AlbumList();
            try {
                //System.out.println("Album name passed in: " + album.getName());
                aL.editAlbum(album.getId(), simc.getInputText());
                //System.out.println("input text is: " + simc.getInputText());
                simc.closeModal();
                homeController.refreshAlbumFlowPane();
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
        modalStage.setScene(CreateScene.createDoubleInputModalScene(root));
        modalStage.show();

        ConfirmationModalController cmc = loader.getController();
        cmc.setTitleText("Are you sure you want to delete this album?");
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
        System.out.println("setting to album: " + album.getName());

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


//    public void setCurrentUser(String str){
//        //System.out.println("Check 1: " + str);
//        UserList ul = new UserList();
//        User u = ul.getUser(str);
//        //System.out.println("Check 2: " + u.getUsername());
//        this.user = u.getUsername();
//        //System.out.println("Check 3: " + this.user);
//    }
//    public String getUser(){
//        //System.out.println("Check 4: " + this.user);
//        return this.user;
//    }

}
