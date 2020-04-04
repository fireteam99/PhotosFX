package com.photos.controllers;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.transform.Translate;
import javafx.util.Duration;

public class SidebarController {
    @FXML
    VBox sidebarContainer;

    @FXML
    Button home;

    @FXML
    Button search;

    @FXML
    Button logout;

    private TranslateTransition showSidebar;
    private TranslateTransition hideSidebar;
    private boolean sidebarVisible = false;

    public void initialize() {
        showSidebar = new TranslateTransition(Duration.millis(250), sidebarContainer);
        showSidebar.setByX(200);
        showSidebar.setOnFinished(event -> sidebarVisible = true);

        hideSidebar = new TranslateTransition(Duration.millis(250), sidebarContainer);
        hideSidebar.setByX(-200);
        hideSidebar.setOnFinished(event -> sidebarVisible = false);
    }

    public void toggleVisibility() {
        if (sidebarVisible) {
            showSidebar.stop();
            hideSidebar.play();
        } else {
            hideSidebar.stop();
            showSidebar.play();
        }
    }
}
