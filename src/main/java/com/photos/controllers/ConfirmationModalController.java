package com.photos.controllers;

import com.photos.util.ButtonStyle;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * ConfirmationModalController class used to create confirmation modals
 * @author Robert Cheng, Ray Sy
 */
public class ConfirmationModalController {

    @FXML
    private Button closeButton;

    @FXML
    private Label modalTitle;

    @FXML
    private Label modalMessage;

    @FXML
    private Button confirmButton;

    @FXML
    private Button cancelButton;

    /**
     * closes the modal
     */
    @FXML
    public void closeModal() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * sets the title text of the modal
     * @param title String
     */
    public void setTitleText(String title) {
        modalTitle.setText(title);
    }

    /**
     * sets the message text of  the modal
     * @param message
     */
    public void setMessageText(String message) {
        modalMessage.setText(message);
    }

    /**
     * sets the action of the confirmation button
     * @param e EventHandler<ActionEvent>
     */
    public void setConfirmButtonAction(EventHandler<ActionEvent> e) {
        confirmButton.setOnAction(e);
    }

    public void setConfirmButtonText(String s) {
        confirmButton.setText(s);
    }

    /**
     * sets the style of the confirmation button
     * @param buttonStyle ButtonStyle
     */
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

}
