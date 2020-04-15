package com.photos.controllers;

import com.photos.util.ButtonStyle;
import com.photos.util.TextStyle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * DoubleInputModalController class: sets up screens with double input
 * @author Robert Cheng, Ray Sy
 */
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

    /**
     * closes the screen
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
     * sets the message text of the modal
     * @param message
     */
    public void setMessageText(String message) {
        modalMessage.setText(message);
    }

    /**
     * sets message visibility
     * @param b Boolean
     */
    public void setMessageVisibility(boolean b) {
        modalMessage.setVisible(b);
        modalMessage.setManaged(b);
    }

    /**
     * sets the style of the message text
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

    /**
     * sets up the input label text
     * @param s String
     */
    public void setInputLabel1Text(String s) {
        inputLabel1.setText(s);
    }

    /**
     * gets the input text
     * @return String
     */
    public String getInput1Text() {
        return input1.getText().trim();
    }

    /**
     * sets the input text
     * @param s String
     */
    public void setInput1Text(String s) {
        input1.setText(s);
    }

    /**
     * sets the prompt text for input
     * @param s String
     */
    public void setInput1PromptText(String s) {
        input1.setPromptText(s);
    }

    /**
     * sets input label text
     * @param s String
     */
    public void setInputLabel2Text(String s) {
        inputLabel2.setText(s);
    }

    /**
     * gets input text
     * @return String
     */
    public String getInput2Text() {
        return input2.getText().trim();
    }

    /**
     * sets input text
     * @param s String
     */
    public void setInput2Text(String s) {
        input2.setText(s);
    }

    /**
     * sets input prompt text
     * @param s String
     */
    public void setInput2PromptText(String s) {
        input2.setPromptText(s);
    }

    /**
     * sets confirmation button text
     * @param s String
     */
    public void setConfirmButtonText(String s) {
        confirmButton.setText(s);
    }

    /**
     * sets the action of the confirm button
     * @param e EventHandler<ActionEvent>
     */
    public void setConfirmButtonAction(EventHandler<ActionEvent> e) {
        confirmButton.setOnAction(e);
    }

    /**
     * sets the confirm button style
     * @param buttonStyle ButtonStyle
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
