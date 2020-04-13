package com.photos.controllers;

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

    public void initialize() {
        headerController.setTitle("Home");
        headerController.setMenuButtonAction(e -> sidebarController.toggleVisibility());

        Preferences userPreferences = Preferences.userRoot();
        String currentUser = userPreferences.get("sessionUser","");
        UserList ul = new UserList();

        for (Album a : ul.getUser(currentUser).getAlbums()){
            AlbumCardController acc = new AlbumCardController();
            acc.setAlbumName(a.getAlbumName());
            //acc.setThumbnail(a.getPhotos().get(0).getImage());
            observableList.add(acc);
        }
        albumFlowPane.getChildren().addAll((Node) observableList);
        //Scene s = albumCardController
    }

    @FXML
    public void createAlbum(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/createAlbum.fxml"));
        //----send user info to the createAlbumController----//
//        CreateAlbumController c = new CreateAlbumController();
//        c.currentUser(user);
        Parent root = loader.load();
        Node n = (Node) event.getSource();
        Stage stage=(Stage) n.getScene().getWindow();
        Scene scene = new Scene(root, 750, 500);
        stage.setScene(scene);
        stage.show();
    }


    public void currUser(String s){
        this.user = s;
    }
}
