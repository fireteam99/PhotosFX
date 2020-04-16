package com.photos.controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.util.Map;

/**
 * TagController class used to handle Tags
 * @author Robert Cheng, Ray Sy
 */
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

    /**
     * allows user to edit tags
     */
    public void editTag() {
        System.out.println("Editing tag");
    }

    /**
     * sets the tags
     * @param tags Map
     * @param tagKey String
     */
    public void setTags(Map<String, String> tags, String tagKey) {
        this.tags = tags;
        this.tagKey = tagKey;

        key.setText(tagKey);
        value.setText(tags.get(tagKey));
    }

    /**
     * sets the edit tag button action
     * @param e EventHandler<MouseEvent>
     */
    public void setEditTagAction(EventHandler<MouseEvent> e) {
        container.addEventHandler(MouseEvent.MOUSE_CLICKED, e);
    }

    /**
     * sets the delete action of the delete button
     * @param e EventHandler<ActionEvent>
     */
    public void setDeleteTagAction(EventHandler<ActionEvent> e) {
        delete.setOnAction(e);
    }

    public void initialize() {

    }

}
