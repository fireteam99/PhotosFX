package com.photos.controllers;

import com.photos.models.*;
import com.photos.util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

/**
 * SlideshowController class used for displaying the slideshow
 * @author Robert Cheng, Ray Sy
 */
public class SlideshowController {
    private Picture picture;

    private Picture leftPicture;

    private Picture rightPicture;

    private Album album;

    private List<Picture> pictures;

    private int position;

    @FXML
    Button closeButton;

    /**
     * exits the slideshow
     * @throws IOException
     */
    @FXML
    public void exit() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/albumDetails.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) container.getScene().getWindow();
        Scene scene = CreateScene.createNormalScene(root);
        stage.setScene(scene);
        stage.show();
        AlbumDetailsController albumDetailsController = loader.getController();
        albumDetailsController.setAlbum(album);
    }

    @FXML
    StackPane container;

    @FXML
    HBox menuButtonContainer;

    @FXML
    ScrollPane imageDetailsContainer;

    @FXML
    MenuItem edit;

    /**
     * allows user to edit a picture from slideshow
     * @throws IOException
     */
    @FXML
    public void editPicture() throws IOException {
        // redirect to edit picture page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/editPicture.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) container.getScene().getWindow();
        Scene scene = CreateScene.createNormalScene(root);
        stage.setScene(scene);
        EditPictureController epc = loader.getController();
        epc.setPicture(picture);
        epc.setPrevScreen(ScreenHistory.SLIDESHOW);
        stage.show();
    }

    @FXML
    MenuItem copy;

    /**
     * copy picture
     * @throws IOException
     */
    @FXML
    public void copyPicture() throws IOException {
        // determine where to copy picture by using combo modal
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/comboBoxModal.fxml"));
        Parent root = loader.load();
        Stage modalStage = CreateStage.createModalStage();
        modalStage.setScene(CreateScene.createComboBoxModalScene(root));
        modalStage.show();

        ComboBoxModalController cbmc = loader.getController();
        cbmc.setTitleText("Copy Picture");
        cbmc.setComboLabelText("Destination Album");
        cbmc.setConfirmButtonText("Copy");

        // get a list of all the availible albums
        UserList userList = new UserList();

        Preferences userPreferences = Preferences.userRoot();
        String sessionUserId = userPreferences.get("sessionUser", "");

        if (sessionUserId.isEmpty()) {
            throw new IllegalStateException("User must be logged in to view slideshow");
        }

        User user = userList.getUser(sessionUserId);
        List<Album> albums = user.getAlbums();

        List<String> albumNames = albums.stream().map(a -> a.getName()).collect(Collectors.toList());

        cbmc.setComboBoxValues(albumNames);

        cbmc.setConfirmButtonStyle(ButtonStyle.CONFIRM);

        cbmc.setMessageVisibility(false);

        // set confirm button action
        cbmc.setConfirmButtonAction(e -> {
            AlbumList albumList = new AlbumList();
            String destinationAlbumName = cbmc.getComboBoxValue();

            if (destinationAlbumName == null) {
                cbmc.setMessageVisibility(true);
                cbmc.setMessageText("Please select an album to copy to.");
                cbmc.setMessageStyle(TextStyle.DANGER);
            } else {
                Album destinationAlbum = albums.stream().filter(a -> a.getName().equals(destinationAlbumName)).findFirst().orElse(null);;
                if (destinationAlbum == null) {
                    throw new IllegalStateException("what just happened???");
                }

                // duplicate the picture
                Picture duplicatePicture = Picture.deepCopy(picture);

                // add it to selected album
                try {
                    destinationAlbum.addPicture(duplicatePicture);
                } catch (IOException ex) {
                    // TODO: make useful
                    ex.printStackTrace();
                }
                // refresh
                try {
                    refreshSlideshow();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // close out of the modal
                cbmc.closeModal();
            }

        });
    }

    @FXML
    Label name;

    @FXML
    Label currentPosition;

    @FXML
    ImageView imageView;

    @FXML
    Label date;

    @FXML
    Label caption;

    @FXML
    Button moveLeft;

    /**
     * move to the left in slideshow
     * @throws IOException
     */
    @FXML
    public void moveLeftPicture() throws IOException {
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("selectedPicture", leftPicture.getId());
        setPicture(leftPicture);
    }

    @FXML
    Button moveRight;

    /**
     * move to the right in slideshow
     * @throws IOException
     */
    @FXML public void moveRightPicture() throws IOException {
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.put("selectedPicture", rightPicture.getId());
        setPicture(rightPicture);
    }

    @FXML
    FlowPane flowPane;

    @FXML
    Button addTagButton;

    /**
     * allows user to add a tag to a selected picture
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
                picture.getTags().put(dimc.getInput1Text(), dimc.getInput2Text());

                // serialize changes
                PictureList pictureList = new PictureList();
                pictureList.editPictureTags(picture.getId(), picture.getTags());

                // close the modal
                dimc.closeModal();

                // refresh the tags
                refreshSlideshow();

            } catch (IOException ex) {
                ex.printStackTrace();
                dimc.setMessageVisibility(true);
                dimc.setMessageStyle(TextStyle.DANGER);
                dimc.setMessageText("Unexpected error, please try again.");
            }
        });
    }


    /**
     * setter method for the slideshow
     * @param picture Picture
     * @throws IOException
     */
    public void setPicture(Picture picture) throws IOException {
        // if the picture is null grab it from preferences instead
        if (picture == null) {
            Preferences userPreferences = Preferences.userRoot();
            String selectedPictureId = userPreferences.get("selectedPicture", "");
            this.picture = new PictureList().getPicture(selectedPictureId);
        } else {
            this.picture = picture;
        }
        refreshSlideshow();
    }

    /**
     * refresh method for slideshow - reflects any changes made to the album
     * @throws IOException
     */
    public void refreshSlideshow() throws IOException {
        // if the picture is null get it through preferences
        Preferences userPreferences = Preferences.userRoot();

        if (picture == null) {
            String selectedPictureId = userPreferences.get("selectedPicture", "");
            if (selectedPictureId.isEmpty()) {
                throw new IllegalStateException("No picture selected...?");
            }
            PictureList pl = new PictureList();
            picture = pl.getPicture(selectedPictureId);
        }

        // get the selected album through preferences
        AlbumList albumList = new AlbumList();
        String selectedAlbumId = userPreferences.get("selectedAlbum", "");

        if (selectedAlbumId.isEmpty()) {
            album = albumList.getAlbum(picture.getAlbum());
        } else {
            album = albumList.getAlbum(selectedAlbumId);
        }

        // fill in image info
        pictures = album.getPictures();

        if (pictures.isEmpty()) {
            // if no pictures available set the visibility to false
            imageDetailsContainer.setVisible(false);
            imageDetailsContainer.setManaged(false);
        } else {
            // set image details visibility to true
            imageDetailsContainer.setVisible(true);
            imageDetailsContainer.setManaged(true);

            List<Picture> sortedPictures = pictures.stream().sorted(Comparator.comparing(Picture::getName)).collect(Collectors.toList());

            // find the current position based on the image specified
            for (int i = 0; i < sortedPictures.size(); i++) {
                if (sortedPictures.get(i).getId().equals(picture.getId())) {
                    position = i;
                    break;
                } else if (i == sortedPictures.size() - 1) {
                    throw new IllegalStateException("Picture is not in album...?");
                }
            }

            // set the name
            name.setText(picture.getName());

            // set the current position
            currentPosition.setText(String.format("%d / %d", position + 1, pictures.size()));

            Image image = new Image(picture.getFile().toURI().toString());

            double aspectRatio = image.getWidth() / image.getHeight();

            int maxHeight = 470;
            int maxWidth = 700;

            // dynamically adjust the height/width of image based on which one is larger
            if (image.getHeight() > image.getWidth()) { // portrait
                imageView.setFitWidth(0);
                imageView.setFitHeight(maxHeight);
                double realWidth = imageView.getFitHeight() * aspectRatio;
                if (realWidth > maxWidth) {
                    imageView.setFitWidth(maxWidth);
                }
            } else { // landscape
                imageView.setFitHeight(0);
                imageView.setFitWidth(maxWidth);
                double realHeight = imageView.getFitWidth() / aspectRatio;
                if (realHeight > maxHeight) {
                    imageView.setFitHeight(maxHeight);
                }
            }

            // set the image
            imageView.setImage(image);

            // set the date
            LocalDateTime localDateTime = picture.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            date.setText(localDateTime.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL).withLocale(Locale.getDefault())));

            // set the captions
            caption.setText(picture.getCaption());

            // set the tags
            flowPane.getChildren().clear();

            // add keys into flow pane
            SortedMap<String, String> sortedTags = new TreeMap<>(picture.getTags());

            for (Map.Entry<String, String> entry: sortedTags.entrySet()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/tag.fxml"));
                Parent root = loader.load();
                TagController tagController = loader.getController();
                tagController.setTags(picture.getTags(), entry.getKey());

                // set action of delete button
                tagController.setDeleteTagAction(e -> {
                    // delete entry from map
                    picture.getTags().remove(entry.getKey());

                    // make the change permanent
                    PictureList pictureList = new PictureList();
                    try {
                        pictureList.editPictureTags(picture.getId(), picture.getTags());
                    } catch (IOException ex) {
                        // TODO: dialog box that something went wrong
                        ex.printStackTrace();
                    }

                    // refresh the flow pane
                    try {
                        refreshSlideshow();
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
                                picture.getTags().remove(entry.getKey());

                                // insert new pair (will overwrite duplicate keys/values)
                                picture.getTags().put(dimc.getInput1Text(), dimc.getInput2Text());

                                // close the modal
                                dimc.closeModal();

                                // serialize the change
                                PictureList pictureList = new PictureList();
                                pictureList.editPictureTags(picture.getId(), picture.getTags());

                                // refresh the user list
                                refreshSlideshow();

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
            flowPane.getChildren().add(addTagButton);

            // set the left picture
            if (position == 0) { // wrap around
                leftPicture = sortedPictures.get(sortedPictures.size() - 1);
            } else {
                leftPicture = sortedPictures.get(position - 1);
            }

            // set the right picture
            if (position == sortedPictures.size() - 1) { // wrap around
                rightPicture = sortedPictures.get(0);
            } else {
                rightPicture = sortedPictures.get(position + 1);
            }

        }

    }
}
