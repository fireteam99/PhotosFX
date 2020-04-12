package com.photos.controllers;

import javafx.fxml.FXML;

public class AlbumDetailsController {
    @FXML
    protected HeaderController headerController;

    @FXML
    protected SidebarController sidebarController;

    public void initialize() {
        headerController.setTitle("Album Name Goes Here");
        headerController.setMenuButtonAction(e -> sidebarController.toggleVisibility());
    }

}
