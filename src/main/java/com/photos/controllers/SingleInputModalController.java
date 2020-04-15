package com.photos.controllers;

import com.photos.util.ButtonStyle;
import com.photos.util.TextStyle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;

public class SingleInputModalController {
    @FXML
    private Button closeButton;

    @FXML
    private Label modalTitle;

    @FXML
    private Label modalMessage;

    @FXML
    private Label inputLabel;

    @FXML
    private TextField input;

    @FXML
    private Button confirmButton;

    @FXML
    private Button cancelButton;

    @FXML
    public void closeModal() {
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

    public void setMessageStyle(TextStyle textStyle) {
        switch (textStyle) {
            case DANGER: {
                modalMessage.getStyleClass().add("text-danger");
                break;
            }
            case NEUTRAL: {
                modalMessage.getStyleClass().remove("text-danger");
            }
        }
    }

    public void setInputLabelText(String s) {
        inputLabel.setText(s);
    }

    public String getInputLabelText() {
        return inputLabel.getText().trim();
    }

    public void setInputPromptText(String s) {
        input.setPromptText(s);
    }

    public void setConfirmButtonText(String s) {
        confirmButton.setText(s);
    }

    public void setConfirmButtonAction(EventHandler<ActionEvent> e) {
        confirmButton.setOnAction(e);
    }

    public void setConfirmButtonStyle(ButtonStyle buttonStyle) {
        switch (buttonStyle) {
            case CONFIRM: {
                confirmButton.getStyleClass().removeAll(new String[]{"btn-confirm", "btn-neutral", "btn-danger"});
                confirmButton.getStyleClass().add("btn-confirm");
                break;
            }
            case DANGER: {
                confirmButton.getStyleClass().removeAll(new String[]{"btn-confirm", "btn-neutral", "btn-danger"});
                confirmButton.getStyleClass().add("btn-danger");
                break;
            }
            default: {
                confirmButton.getStyleClass().removeAll(new String[]{"btn-confirm", "btn-neutral", "btn-danger"});
                confirmButton.getStyleClass().add("btn-neutral");
                break;
            }
        }
    }

    public void initialize() {

    }

}
