package com.photos.controllers;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class AdminController {

    @FXML
    private ListView<String> adminListView;

    @FXML
    private Button adminCreateUserButton;

    @FXML
    void adminCreateUser(ActionEvent event) {

    }
    public void initialize() {
        adminListView.getItems().add("User1");
        adminListView.getItems().add("User2");
        adminListView.getItems().add("User3");

    }
    public AdminController() {
        adminListView = new ListView<>();
    }

}

