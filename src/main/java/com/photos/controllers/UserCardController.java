package com.photos.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

/**
 * UserCardController class used to populate admin page
 * @author Robert Cheng, Ray Sy
 */
public class UserCardController {
    @FXML
    private BorderPane borderPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    /**
     * sets the name label text
     * @param s String
     */
    public void setNameLabelText(String s) {
        nameLabel.setText(s);
    }

    /**
     * sets the action of the edit button
     * @param e EventHandler<ActionEvent>
     */
    public void setEditButtonAction(EventHandler<ActionEvent> e) {
        editButton.setOnAction(e);
    }

    /**
     * sets the availability of the edit button
     * @param b Boolean
     */
    public void setEditButtonDisable(Boolean b) {
        editButton.setDisable(b);
    }

    /**
     * sets the delete button action
     * @param e EventHandler<ActionEvent>
     */
    public void setDeleteButtonAction(EventHandler<ActionEvent> e) {
        deleteButton.setOnAction(e);
    }

    /**
     * enable/disables the delete button
     * @param b Boolean
     */
    public void setDeleteButtonDisable(Boolean b) {
        deleteButton.setDisable(b);
    }
}
