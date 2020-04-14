package com.photos.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;


public class UserCardController {
    @FXML
    private BorderPane borderPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    public void setNameLabelText(String s) {
        nameLabel.setText(s);
    }

    public void setEditButtonAction(EventHandler<ActionEvent> e) {
        editButton.setOnAction(e);
    }
    public void setEditButtonDisable(Boolean b) {
        editButton.setDisable(b);
    }

    public void setDeleteButtonAction(EventHandler<ActionEvent> e) {
        deleteButton.setOnAction(e);
    }
    public void setDeleteButtonDisable(Boolean b) {
        deleteButton.setDisable(b);
    }
}
