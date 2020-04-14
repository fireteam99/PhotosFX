package com.photos.controllers;

import com.photos.models.Album;
import com.photos.models.AlbumList;
import com.photos.models.User;
import com.photos.models.UserList;
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
import java.util.List;
import java.util.prefs.Preferences;

public class HomeController {
    private String user;

    @FXML
    private FlowPane albumFlowPane;

    @FXML
    protected HeaderController headerController;

    @FXML
    protected SidebarController sidebarController;

    @FXML
    private Button createAlbumButton;

    @FXML
    protected CreateAlbumController createAlbumController;

    @FXML
    protected SingleInputModalController singleInputModalController;

    public void initialize() throws IOException {
        headerController.setTitle("Home");
        headerController.setMenuButtonAction(e -> sidebarController.toggleVisibility());

        refreshAlbumFlowPane();
    }

    public void refreshAlbumFlowPane() throws IOException {
        // remove all children from flowpane
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

        // generate an album card per album
        for (Album album: albums) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/albumCard.fxml"));
            Parent root = loader.load();
            AlbumCardController acc = loader.getController();
            acc.setAlbum(album);

            // add the album card to flowpane
            albumFlowPane.getChildren().add(root);
        }

    }

    @FXML
    public void createAlbum(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/createAlbum.fxml"));
        //----send user info to the createAlbumController----//
//        CreateAlbumController c = new CreateAlbumController();
//        c.currentUser(user);
        Parent root = loader.load();
        Node n = (Node) event.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        Scene scene = new Scene(root, 750, 500);
        stage.setScene(scene);
        stage.show();
    }
}
