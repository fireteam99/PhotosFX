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

    public void setComboLabelText(String s) {
        comboLabel.setText(s);
    }

    public void setComboBoxValues(List<String> values) {
        comboBox.getItems().addAll(values);
    }

    public String getComboBoxValue() {
        if (comboBox.getValue() == null) {
            return null;
        }
        return comboBox.getValue().toString();
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

//    public void initialize() {
//        setComboBoxValues(Arrays.asList("val1", "val2", "val3"));
//    }

}
