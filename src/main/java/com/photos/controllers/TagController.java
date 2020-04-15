package com.photos.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.util.Map;

public class TagController {
    Map<String, String> tags;
    String tagKey;

    @FXML
    BorderPane container;

    @FXML
    Label key;

    @FXML
    Label value;

    @FXML
    Button delete;

    public void editTag() {
        System.out.println("Editing tag");
    }

    public void setTags(Map<String, String> tags, String tagKey) {
        this.tags = tags;
        this.tagKey = tagKey;

        key.setText(tagKey);
        value.setText(tags.get(tagKey));
    }

    public void setEditTagAction(EventHandler<MouseEvent> e) {
        container.addEventHandler(MouseEvent.MOUSE_CLICKED, e);
    }

    public void setDeleteTagAction(EventHandler<ActionEvent> e) {
        delete.setOnAction(e);
    }

    public void initialize() {

    }

}
