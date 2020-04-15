package com.photos.controllers;

import com.photos.models.*;
import com.photos.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.prefs.Preferences;

/**
 * EditPictureController class: allows changes to be made to an individual picture object
 * @author Robert Cheng, Ray Say
 */
public class EditPictureController {
    private Picture picture;

    private Map<String, String> tags;

    private File file;

    private ScreenHistory prevScreen;

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
    FlowPane flowPane;

    @FXML
    ImageView imageView;

    @FXML
    Label filePath;

    @FXML
    Button editFilePath;

    @FXML
    Button cancelButton;

    @FXML
    Button saveButton;

    @FXML
    Button addButton;

    /**
     * saves the changes made to a Picture object
     * @throws IOException
     */
    @FXML
    public void saveChanges() throws IOException {
        PictureList pictureList = new PictureList();
        System.out.println(captionInput.getText());
        pictureList.editPicture(picture.getId(), picture.getAlbum(), nameInput.getText().trim(), captionInput.getText().trim(), tags, file);
        exitScreen();
    }

    /**
     * cancel editing a Picture
     * @throws IOException
     */
    @FXML
    public void cancel() throws IOException {
        System.out.println("Cancel");
        exitScreen();
    }

    /**
     * edit a file (path to image)
     */
    @FXML
    public void editFile() {
        Stage stage = (Stage) mainStackPane.getScene().getWindow();
        FileChooser.ExtensionFilter imageFilter = new FileChooser.ExtensionFilter(
            "Image Files", "*.jpg", "*.jpeg", "*.png", "*.gif");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(imageFilter);

        file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            imageView.setImage(new Image(file.toURI().toString()));
        }
    }

    /**
     * sets up the edit picture screen
     * @throws IOException
     */
    public void initialize() throws IOException {
        // deals with sidebar blocking actions
        mainStackPane.setPickOnBounds(false);
        sidebarHBox.setPickOnBounds(false);
        headerController.setTitle("Edit Picture");
        headerController.setMenuButtonAction(e -> sidebarController.toggleVisibility());
        prevScreen = ScreenHistory.HOME;
    }

    /**
     * sets the picture object
     * @param picture Picture
     * @throws IOException
     */
    public void setPicture(Picture picture) throws IOException {
        this.picture = picture;
        this.tags = picture.getTags();
        this.file = picture.getFile();

        nameInput.setText(picture.getName());
        captionInput.setText(picture.getCaption());

        // TODO: set the tags
        refreshTags();

        imageView.setImage(new Image(picture.getFile().toURI().toString()));

        filePath.setText(picture.getFile().getPath());
    }

    /**
     * sets the previous screen
     * @param screenHistory ScreenHistory
     */
    public void setPrevScreen(ScreenHistory screenHistory) {
        prevScreen = screenHistory;
    }

    /**
     * gets the latest tags for a Picture object after changes are made
     * @throws IOException
     */
    public void refreshTags() throws IOException {
        // clear the flow pane of all tags
        flowPane.getChildren().clear();

        // add keys into flow pane
        SortedMap<String, String> sortedTags = new TreeMap<>(tags);

        for (Map.Entry<String, String> entry: sortedTags.entrySet()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tag.fxml"));
            Parent root = loader.load();
            TagController tagController = loader.getController();
            tagController.setTags(tags, entry.getKey());

            // set action of delete button
            tagController.setDeleteTagAction(e -> {
                // delete entry from map
                tags.remove(entry.getKey());
                // refresh the flow pane
                try {
                    refreshTags();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            // set the action of the edit button
            tagController.setEditTagAction(e -> {
                // display double input modal
                FXMLLoader modalLoader = new FXMLLoader(getClass().getResource("/fxml/doubleInputModal.fxml"));
                try {
                    Parent modalRoot = modalLoader.load();
                    Stage modalStage = new CreateStage().createModalStage();
                    modalStage.setScene(CreateScene.createDoubleInputModalScene(modalRoot));
                    modalStage.show();

                    DoubleInputModalController dimc = modalLoader.getController();
                    dimc.setTitleText("Edit Tag");
                    dimc.setMessageVisibility(false);
                    dimc.setInputLabel1Text("Key");
                    dimc.setInput1Text(entry.getKey());
                    dimc.setInputLabel2Text("Value");
                    dimc.setInput2Text(entry.getValue());
                    dimc.setConfirmButtonAction(event -> {
                        try {
                            // remove original key value pair
                            tags.remove(entry.getKey());

                            // insert new pair (will overwrite duplicate keys/values)
                            tags.put(dimc.getInput1Text(), dimc.getInput2Text());

                            // close the modal
                            dimc.closeModal();

                            // refresh the user list
                            refreshTags();
                        } catch (IOException ex) {
                            dimc.setMessageVisibility(true);
                            dimc.setMessageStyle(TextStyle.DANGER);
                            dimc.setMessageText("Unexpected error, please try again.");
                        }
                    });
                    dimc.setConfirmButtonStyle(ButtonStyle.CONFIRM);
                } catch (IOException ex) {
                    // TODO: Show a modal on why it failed
                    ex.printStackTrace();
                }
            });

            // add the album card to flow pane
            flowPane.getChildren().add(root);
        }

        // add in the add button last
        flowPane.getChildren().add(addButton);
    }

    /**
     * adds tag(s) to a Picture object
     * @throws IOException
     */
    @FXML
    public void addTag() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/doubleInputModal.fxml"));
        Parent root = loader.load();
        Stage modalStage = CreateStage.createModalStage();
        modalStage.setScene(CreateScene.createDoubleInputModalScene(root));
        modalStage.show();

        DoubleInputModalController dimc = loader.getController();
        dimc.setTitleText("Create Tag");
        dimc.setMessageVisibility(false);
        dimc.setInputLabel1Text("Key");
        dimc.setInputLabel2Text("Value");

        dimc.setConfirmButtonText("Create");
        dimc.setConfirmButtonStyle(ButtonStyle.CONFIRM);
        dimc.setConfirmButtonAction(e -> {
            UserList userList = new UserList();
            User user = new User(dimc.getInput1Text(), dimc.getInput2Text());
            try {
                // add the new key value pair in (will override any duplicate key/values)
                tags.put(dimc.getInput1Text(), dimc.getInput2Text());

                // close the modal
                dimc.closeModal();

                // refresh the user list
                refreshTags();
                System.out.println("test");
                System.out.println(tags.toString());

            } catch (IOException ex) {
                ex.printStackTrace();
                dimc.setMessageVisibility(true);
                dimc.setMessageStyle(TextStyle.DANGER);
                dimc.setMessageText("Unexpected error, please try again.");
            }
        });
    }

    /**
     * exits the edit picture module
     * @throws IOException
     */
    public void exitScreen() throws IOException {
        switch (prevScreen) {
            case SLIDESHOW: {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/slideshow.fxml"));
                Parent root = loader.load();
                Stage stage = (Stage) mainStackPane.getScene().getWindow();
                Scene scene = CreateScene.createNormalScene(root);
                stage.setScene(scene);
                stage.show();
                SlideshowController slideshowController = loader.getController();
                if (picture.getId() == null) {
                    slideshowController.setPicture(null);
                } else {
                    slideshowController.setPicture(new PictureList().getPicture(picture.getId()));

                }
                break;
            }
            case ALBUM_DETAILS: {
                // try to get album from preferences
                Preferences userPreferences = Preferences.userRoot();
                String selectedAlbumId = userPreferences.get("selectedAlbum", "");

                if (selectedAlbumId.isEmpty()) {
                    navigateHome();
                } else {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/albumDetails.fxml"));
                    Parent root = loader.load();
                    Stage stage = (Stage) mainStackPane.getScene().getWindow();
                    Scene scene = CreateScene.createNormalScene(root);
                    stage.setScene(scene);
                    stage.show();
                    AlbumDetailsController abc = loader.getController();
                    abc.setAlbum(new AlbumList().getAlbum(selectedAlbumId));
                }
                break;
            }
            default: {
                navigateHome();
                break;
            }
        }
    }

    /**
     * takes user back to the home page
     * @throws IOException
     */
    private void navigateHome() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) mainStackPane.getScene().getWindow();
        Scene scene = CreateScene.createNormalScene(root);
        stage.setScene(scene);
        stage.show();

    }

}
