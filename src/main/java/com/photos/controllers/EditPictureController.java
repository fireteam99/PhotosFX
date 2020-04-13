package com.photos.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class EditPictureController {
    @FXML
    HeaderController headerController;

    @FXML
    SidebarController sidebarController;

    @FXML
    HBox sidebarHBox;

    @FXML
    StackPane mainStackPane;

    @FXML
    TextField nameInput;

    @FXML
    TextArea captionInput;

    @FXML
    FlowPane flowpane;

    @FXML
    Button cancelButton;

    @FXML
    Button saveButton;

    public void initialize() {
        mainStackPane.setPickOnBounds(false);
        sidebarHBox.setPickOnBounds(false);
        headerController.setTitle("Home");
        headerController.setMenuButtonAction(e -> {
            sidebarController.toggleVisibility();
        });
    }

    @FXML
    public void exit() {
        System.out.println("Cancel");
    }

    @FXML
    public void saveChanges() {
        System.out.println("Save");
    }
}
