package com.photos.controllers;

import javafx.fxml.FXML;

public class homeController {
    @FXML
    protected HeaderController headerController;

    @FXML
    protected SidebarController sidebarController;

    public void initialize() {
        headerController.setTitle("Home");
        headerController.setMenuButtonAction(e -> sidebarController.toggleVisibility());
    }
}
