package com.photos.controllers;

import com.photos.models.Album;
import com.photos.models.AlbumList;
import com.photos.models.User;
import com.photos.models.UserList;
import com.photos.util.ButtonStyle;
import com.photos.util.CreateScene;
import com.photos.util.CreateStage;
import com.photos.util.TextStyle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

/**
 * HomeController class: loads in all albums for the current user
 * @author Robert Cheng
 */
public class HomeController {
    @FXML
    private FlowPane albumFlowPane;

    @FXML
    protected HeaderController headerController;

    @FXML
    protected SidebarController sidebarController;

    @FXML
    private Button createAlbumButton;

    @FXML
    protected SingleInputModalController singleInputModalController;

    /**
     * initialize function sets up header and sidebar buttons
     * @throws IOException
     */
    public void initialize() throws IOException {
        headerController.setTitle("Home");
        headerController.setMenuButtonAction(e -> sidebarController.toggleVisibility());

        refreshAlbumFlowPane();
    }

    /**
     * refreshes the flow pane to reflect any changes to album cards
     * @throws IOException
     */
    public void refreshAlbumFlowPane() throws IOException {
        // remove all children from flow pane
        albumFlowPane.getChildren().clear();

        // get the logged in user via preferences
        Preferences userPreferences = Preferences.userRoot();
        String loggedInUserId = userPreferences.get("sessionUser", "");
        if (loggedInUserId.isEmpty()) {
            throw new IllegalStateException("User must be logged in to access home screen.");
        }

        UserList userList = new UserList();
        User user = userList.getUser(loggedInUserId);

        // get the albums that belong to the user
        List<Album> albums = user.getAlbums();

        // sort albums by name
        List<Album> sortedAlbums = albums.stream().sorted(Comparator.comparing(Album::getName)).collect(Collectors.toList());

        // generate an album card per album
        for (Album album: sortedAlbums) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/albumCard.fxml"));
            Parent root = loader.load();
            AlbumCardController acc = loader.getController();
            acc.setAlbum(album);
            acc.setHomeController(this);

            // add the album card to flowpane
            albumFlowPane.getChildren().add(root);
        }

    }

    /**
     * allows user to create a new album
     * @param event ActionEvent
     * @throws IOException
     */
    @FXML
    public void createAlbum(ActionEvent event) throws IOException {
        System.out.println("Creating new album...");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/singleInputModal.fxml"));
        Parent root = loader.load();
        Stage modalStage = CreateStage.createModalStage();
        modalStage.setScene(CreateScene.createDoubleInputModalScene(root));
        modalStage.show();

        SingleInputModalController simc = loader.getController();
        simc.setTitleText("Create New Album");
        simc.setMessageVisibility(false);
        simc.setInputLabelText("Album Name");
        simc.setConfirmButtonText("Add Album");
        simc.setConfirmButtonStyle(ButtonStyle.CONFIRM);
        simc.setConfirmButtonAction(e -> {
            UserList ul = new UserList();
            Preferences userPreferences = Preferences.userRoot();
            String loggedInUserId = userPreferences.get("sessionUser", "");
            Album a = new Album(simc.getInputText(), loggedInUserId);
            try {
                ul.getUser(loggedInUserId).addAlbum(a);
                simc.closeModal();
                refreshAlbumFlowPane();
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
}
