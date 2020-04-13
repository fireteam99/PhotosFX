package com.photos.controllers;

import java.io.IOException;
import java.util.prefs.Preferences;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class AlbumCardController {
    private static String user;

    @FXML
    private MenuButton myMenuBar;

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

    public void initialize(){

    }
    @FXML
    public void viewAlbum(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/albumDetails.fxml"));
        Parent root = loader.load();

//        CurrentUser c = new CurrentUser();
//        this.user = c.getCurrentUser();
        Preferences userPreferences = Preferences.userRoot();
        String str = userPreferences.get("sessionUser", "");
        //System.out.println("POOP: " + str);
        //System.out.println("Check 5: " + str);
        this.user = str;
        AlbumDetailsController adc = loader.getController();
        adc.thisUser(user, albumName.toString());
        //System.out.println("USERNAME (AlbumCard): " + user);

        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        Scene scene = new Scene(root, 750, 500);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void editAlbum(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editAlbum.fxml"));
        //----send the album object to EditAlbumController----//
        EditAlbumController eactr = loader.getController();

        Preferences userPreferences = Preferences.userRoot();
        String str = userPreferences.get("sessionUser","");
        this.user = str;
        //System.out.println("Clicking edit button: " + user);
        //null error bc album name does not exist yet***
        eactr.currUser(user,albumName.getText());
        //
        Parent root = loader.load();
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        Scene scene = new Scene(root, 750, 500);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void deleteAlbum(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/confirmationModal.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) myMenuBar.getScene().getWindow();
        Scene scene = new Scene(root, 750, 500);
        stage.setScene(scene);
        stage.show();
    }

    public void setAlbumName(String s) {
        albumName.setText(s);
    }

    public void setThumbnail(Image i) {
        imageView.setImage(i);
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
