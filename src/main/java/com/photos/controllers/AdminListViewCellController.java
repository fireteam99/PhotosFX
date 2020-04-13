package com.photos.controllers;

import com.photos.models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class AdminListViewCellController extends ListCell<User> {
    private FXMLLoader loader;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Button editButton;

    @FXML
    private Button deleteButton;

    @Override
    protected  void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);
        if (empty || user == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (loader == null) {
                loader = new FXMLLoader(getClass().getResource("/fxml/adminListViewCell.fxml"));
            }
            loader.setController(this);
            try {
                loader.load();
            } catch (IOException e) {
                System.out.println("Failed to load fxml");
                e.printStackTrace();
            }

            if (nameLabel != null) {
                nameLabel.setText(String.valueOf(user.getUsername()));
            } else {
                setText(null);
                setGraphic(null);
            }

            if (editButton != null) {
                editButton.setOnAction(e -> System.out.println("Editing account"));
            } else {
                setText(null);
                setGraphic(null);
            }

            if (deleteButton != null) {
                deleteButton.setOnAction(e -> System.out.println("Deleting account"));
            } else {
                setText(null);
                setGraphic(null);
            }
            setText(null);
            setGraphic(borderPane);

        }
    }
}
