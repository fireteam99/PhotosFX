package com.photos.controllers;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.util.Duration;

public class SidebarController {
    @FXML
    Button closeSidebar;

    @FXML
    Button home;

    @FXML
    Button search;

    @FXML
    Button logout;

    public void initialize() {
        prepareSlideMenuAnimation();
    }

    private void prepareSlideMenuAnimation() {
//        TranslateTransition openSidebar = new TranslateTransition(new Duration())
    }
}
