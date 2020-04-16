package com.photos.controllers;

import com.photos.util.CreateScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * HeaderController class used for setting up the header
 * @author Robert Cheng, Ray Sy
 */
public class HeaderController {
    @FXML
    private Label title;

    @FXML
    private Button menuButton;

    public void initialize() {

    }

    /**
     * set the title of the header
     * @param s
     */
    public void setTitle(String s) {
        title.setText(s);
    }

    /**
     * set the actions of the menu button
     * @param e EventHandler<ActionEvent>
     */
    public void setMenuButtonAction(EventHandler<ActionEvent> e) {
        menuButton.setOnAction(e);
    }

    public void goToSearch(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/search.fxml"));
        Parent root = loader.load();
        Stage stage=(Stage) title.getScene().getWindow();
        Scene scene = CreateScene.createNormalScene(root);
        stage.setScene(scene);
        stage.show();
    }

}
