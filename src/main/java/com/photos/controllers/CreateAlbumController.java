package com.photos.controllers;

import com.photos.models.Album;
import com.photos.models.UserList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateAlbumController {
    private static String user;

    @FXML
    private TextField newAlbumBox;

    @FXML
    private Button submitNewButton;

    @FXML
    private Button cancelNewButton;

    @FXML
    private Button closeNewAlbumButton;

    @FXML
    void cancelNewAlbum(ActionEvent event) throws IOException {
        goBack(event);
    }

    @FXML
    void closeCreateNewAlbum(ActionEvent event) throws IOException {
        goBack(event);
    }

    @FXML
    void submitNewAlbum(ActionEvent event) throws IOException {
        //find user with this username in userList, then add new album object to their list
        UserList uL = new UserList();
        Album newAlbum = new Album(newAlbumBox.getText());

        uL.getUser(user).getAlbums().add(newAlbum);

        System.out.println("Successfully added new album for user: " + user);
        goBack(event);
    }

    public void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        Parent root = loader.load();
        Node n = (Node) event.getSource();
        Stage stage=(Stage) n.getScene().getWindow();
        Scene scene = new Scene(root, 750, 500);
        stage.setScene(scene);
        stage.show();
    }

//    public void currentUser(String s){
//        this.user = s;
//    }


}
