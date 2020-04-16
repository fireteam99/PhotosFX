package com.photos.controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

/**
 * SidebarController class: used for controller classes that have a sidebar menu
 * @author Robert Cheng, Ray Sy
 */
public class SidebarController {
    @FXML
    VBox sidebarContainer;

    @FXML
    Button home;

    @FXML
    Button search;

    @FXML
    Button logout;

    /**
     * logout button takes user back to the login screen
     * @param actionEvent ActionEvent
     * @throws IOException
     */
    @FXML
    public void logout(javafx.event.ActionEvent actionEvent) throws IOException {
        goBack(actionEvent);
    }

    /**
     * takes user back to the home page
     * @param actionEvent ActionEvent
     * @throws IOException
     */
    @FXML
    public void goToHome(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        Parent root = loader.load();
        Node n = (Node) actionEvent.getSource();
        Stage stage=(Stage) n.getScene().getWindow();
        Scene scene = new Scene(root, 1110, 750);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * search album
     * @param actionEvent
     */
    @FXML
    public void searchAlbum(ActionEvent actionEvent) {
    }

    private TranslateTransition showSidebar;
    private TranslateTransition hideSidebar;
    private boolean sidebarVisible = false;

    /**
     * SidebarController initialize method
     */
    public void initialize() {
        showSidebar = new TranslateTransition(Duration.millis(250), sidebarContainer);
        showSidebar.setByX(200);
        showSidebar.setOnFinished(event -> sidebarVisible = true);

        hideSidebar = new TranslateTransition(Duration.millis(250), sidebarContainer);
        hideSidebar.setByX(-200);
        hideSidebar.setOnFinished(event -> sidebarVisible = false);
    }

    /**
     * check if sidebar is currently visible
     * @return Boolean
     */
    public boolean getVisibility() {
        return sidebarVisible;
    }

    /**
     * toggle visibility of the sidebar
     */
    public void toggleVisibility() {
        if (sidebarVisible) {
            showSidebar.stop();
            hideSidebar.play();

        } else {
            sidebarContainer.setVisible(true);
            hideSidebar.stop();
            showSidebar.play();
        }
    }

    /**
     * helper method for logging out
     * @param event ActionEvent
     * @throws IOException
     */
    private void goBack(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = loader.load();
        Node n = (Node) event.getSource();
        Stage stage=(Stage) n.getScene().getWindow();
        Scene scene = new Scene(root, 1110, 750);
        stage.setScene(scene);
        stage.show();
    }

}
