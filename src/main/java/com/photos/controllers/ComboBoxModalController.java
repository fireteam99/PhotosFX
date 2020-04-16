package com.photos.controllers;

import com.photos.util.ButtonStyle;
import com.photos.util.TextStyle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ComboBoxModalController class used for combobox scene
 * @author Robert Cheng, Ray Sy
 */
public class ComboBoxModalController {
    @FXML
    private Button closeButton;

    @FXML
    private Label modalTitle;

    @FXML
    private Label modalMessage;

    @FXML
    private Label comboLabel;

    @FXML
    private ComboBox comboBox;

    @FXML
    private Button confirmButton;

    @FXML
    private Button cancelButton;

    /**
     * closes modal
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
     * @param message String
     */
    public void setMessageText(String message) {
        modalMessage.setText(message);
    }

    /**
     * sets the message visibility of the modal
     * @param b Boolean
     */
    public void setMessageVisibility(boolean b) {
        modalMessage.setVisible(b);
        modalMessage.setManaged(b);
    }

    /**
     * sets the message style of the modal
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
     * sets the combo label text
     * @param s String
     */
    public void setComboLabelText(String s) {
        comboLabel.setText(s);
    }

    /**
     * sets the combo box values
     * @param values List<String>
     */
    public void setComboBoxValues(List<String> values) {
        comboBox.getItems().addAll(values);
    }

    /**
     * gets the combo box value
     * @return String
     */
    public String getComboBoxValue() {
        if (comboBox.getValue() == null) {
            return null;
        }
        return comboBox.getValue().toString();
    }

    /**
     * sets the confirm button text
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
     * sets the button style of the confirm button
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

//    public void initialize() {
//        setComboBoxValues(Arrays.asList("val1", "val2", "val3"));
//    }

}
