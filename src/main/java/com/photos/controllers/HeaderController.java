package com.photos.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

}
