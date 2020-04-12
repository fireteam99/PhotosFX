package com.photos.controllers;

import com.photos.util.ButtonStyle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DoubleInputModalController {
    @FXML
    private Button closeButton;

    @FXML
    private Label modalTitle;

    @FXML
    private Label modalMessage;

    @FXML
    private Label inputLabel1;

    @FXML
    private TextField input1;

    @FXML
    private Label inputLabel2;

    @FXML
    private TextField input2;

    @FXML
    private Button confirmButton;

    @FXML
    private Button cancelButton;

    @FXML
    public void closeButtonAction() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    public void setTitleText(String title) {
        modalTitle.setText(title);
    }

    public void setMessageText(String message) {
        modalMessage.setText(message);
    }

    public void setMessageVisibility(boolean b) {
        modalMessage.setVisible(b);
        modalMessage.setManaged(b);
    }

    public void setInputLabel1Text(String s) {
        inputLabel1.setText(s);
    }

    public void setInput1PromptText(String s) {
        input1.setPromptText(s);
    }

    public void setInputLabel2Text(String s) {
        inputLabel2.setText(s);
    }

    public void setInput2PromptText(String s) {
        input2.setPromptText(s);
    }

    public void setConfirmButtonAction(EventHandler<ActionEvent> e) {
        confirmButton.setOnAction(e);
    }

    public void setConfirmButtonStyle(ButtonStyle buttonStyle) {
        switch (buttonStyle) {
            case CONFIRM: {
                confirmButton.getStyleClass().removeAll();
                confirmButton.getStyleClass().add("btn-confirm");
                break;
            }
            case DANGER: {
                confirmButton.getStyleClass().removeAll();
                confirmButton.getStyleClass().add("btn-danger");
                break;
            }
            default: {
                confirmButton.getStyleClass().removeAll();
                confirmButton.getStyleClass().add("btn-neutral");
                break;
            }
        }
    }

    public void initialize() {
    }

}
