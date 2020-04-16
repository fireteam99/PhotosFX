package com.photos.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

public class SearchController {
    @FXML
    HeaderController headerController;

    @FXML
    SidebarController sidebarController;

    @FXML
    HBox sidebarHBox;

    @FXML
    StackPane mainStackPane;

    public void initialize() {
        headerController.setTitle("Album Name");
        headerController.setMenuButtonAction(e -> sidebarController.toggleVisibility());
        mainStackPane.setPickOnBounds(false);
        sidebarHBox.setPickOnBounds(false);

    }



}
