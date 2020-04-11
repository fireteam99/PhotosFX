package com.photos.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ConfirmationModalController {

    @FXML
    private Button closeButton;

    @FXML
    private void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void setTitle() {

    }

    public void setMessage() {

    }

    public void setDeleteButtonAction() {

    }
}
