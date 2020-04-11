package com.photos.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


public class ConfirmationModalController {

    @FXML
    private Button closeButton;

    @FXML
    private Label confirmationTitle;

    @FXML
    private Label confirmationMessage;

    @FXML
    private Button confirmButton;

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void setTitle(String title) {
        confirmationTitle.setText(title);
    }

    public void setMessage(String message) {
        confirmationMessage.setText(message);
    }

    public void setDeleteButtonAction(EventHandler<ActionEvent> e) {
        confirmButton.setOnAction(e);
    }
}
