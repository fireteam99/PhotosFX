package com.photos.controllers;

import com.photos.models.Album;
import com.photos.models.User;
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

public class EditAlbumController {

    private String user;
    private String albumName;

    @FXML
    private TextField editAlbumBox;

    @FXML
    private Button submitEditButton;

    @FXML
    private Button cancelEditButton;

    @FXML
    private Button closeEditButton;

    @FXML
    void cancelAlbumEdit(ActionEvent event) throws IOException {
        goBack(event);
    }

    @FXML
    void closeAlbumEdit(ActionEvent event) throws IOException {
        goBack(event);
    }

    @FXML
    void submitAlbumEdit(ActionEvent event) throws IOException {
        //get the user object with this user name
        //go into their collection of albums and find the one with this name, then update its name
        UserList ul = new UserList();
        for (Album a : ul.getUser(user).getAlbums()){
            if (a.getAlbumName().equals(albumName)){
                a.changeAlbumName(editAlbumBox.getText());
                break;
            }
        }
        System.out.println("Album name was successfully updated!");
        goBack(event);
    }

    //given the user and the specific album name, store this data
    //and search thru the userList --> find this user and the album that matches this name
    public void currUser(String user, String albumName){
        this.user = user;
        this.albumName = albumName;
        this.editAlbumBox.setText(albumName);
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
}

