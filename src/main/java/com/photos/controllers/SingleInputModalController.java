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

/**
 * SingleInputModalController class is used to create modals that require a
 * single line of input.
 * @author Robert Cheng, Ray Sy
 */
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

    /**
     * close the modal
     */
    @FXML
    public void closeModal() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * sets the title of the modal
     * @param title String
     */
    public void setTitleText(String title) {
        modalTitle.setText(title);
    }

    /**
     * sets the message of the modal
     * @param message String
     */
    public void setMessageText(String message) {
        modalMessage.setText(message);
    }

    /**
     * sets the visibility of the message
     * @param b Boolean
     */
    public void setMessageVisibility(boolean b) {
        modalMessage.setVisible(b);
        modalMessage.setManaged(b);
    }

    /**
     * sets the style of the message
     * @param textStyle TextStyle
     */
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

    public void setInputText(String s) {
        input.setText(s);
    }

    /**
     * sets the input label text
     * @param s String
     */
    public void setInputLabelText(String s) {
        inputLabel.setText(s);
    }

    /**
     * gets the input label text
     * @return String
     */
    public String getInputLabelText() {
        return inputLabel.getText().trim();
    }

    /**
     * sets the input prompt text
     * @param s String
     */
    public void setInputPromptText(String s) {
        input.setPromptText(s);
    }

    /**
     * gets the user input text
     * @return String
     */
    public String getInputText(){ return input.getText().trim();}

    /**
     * sets the text of the confirmation button
     * @param s String
     */
    public void setConfirmButtonText(String s) {
        confirmButton.setText(s);
    }

    /**
     * sets the action of the confirmation button
     * @param e EventHandler<ActionEvent>
     */
    public void setConfirmButtonAction(EventHandler<ActionEvent> e) {
        confirmButton.setOnAction(e);
    }

    /**
     * sets the style of the confirmation button
     * @param buttonStyle
     */
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

}
