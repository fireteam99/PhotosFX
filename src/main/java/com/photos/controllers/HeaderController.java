package com.photos.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HeaderController {
    @FXML
    private Label title;

    @FXML
    private Button menuButton;

    public void initialize() {

    }

    public void setTitle(String s) {
        title.setText(s);
    }

    public void setMenuButtonAction(EventHandler<ActionEvent> e) {
        menuButton.setOnAction(e);
    }

}
