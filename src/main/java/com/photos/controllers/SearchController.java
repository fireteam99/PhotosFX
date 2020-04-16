package com.photos.controllers;

import com.photos.models.*;
import com.photos.util.ButtonStyle;
import com.photos.util.CreateScene;
import com.photos.util.CreateStage;
import com.photos.util.TextStyle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;

public class SearchController {

    private List<Picture> results;

    @FXML
    HeaderController headerController;

    @FXML
    SidebarController sidebarController;

    @FXML
    HBox sidebarHBox;

    @FXML
    StackPane mainStackPane;

    @FXML
    DatePicker startDatePicker;

    @FXML
    DatePicker endDatePicker;

    @FXML
    public void clearDateSearch() {
        startDatePicker.setValue(null);
        endDatePicker.setValue(null);
    }

    @FXML
    FlowPane resultsFlowPane;

    @FXML
    Button clearResultsButton;

    @FXML
    Button createAlbumButton;


    @FXML
    public void searchByDate() throws IOException {
        // get current user using preferences
        Preferences userPreferences = Preferences.userRoot();
        String sessionUserId = userPreferences.get("sessionUser", "");

        if (sessionUserId.isEmpty()) {
            throw new IllegalStateException("User should be logged in.");
        }

        User user = new UserList().getUser(sessionUserId);

        List<Picture> pictures = user.getPictures();

        List<Picture> results;

        if (startDatePicker.getValue() == null && endDatePicker.getValue() == null) {
            results = pictures;
        } else if (startDatePicker.getValue() == null) {
            Date endDate = localDateToDate(endDatePicker.getValue());
            results = pictures.stream().filter(p -> p.getDate().before(endDate)).collect(Collectors.toList());
        } else if (endDatePicker.getValue() == null) {
            Date startDate = localDateToDate(startDatePicker.getValue());
            results = pictures.stream().filter(p -> p.getDate().after(startDate)).collect(Collectors.toList());

        } else {
            Date startDate = localDateToDate(startDatePicker.getValue());
            Date endDate = localDateToDate(endDatePicker.getValue());
            results = pictures.stream().filter(p -> p.getDate().after(startDate) && p.getDate().before(endDate)).collect(Collectors.toList());
        }


        // display the results
        for (Picture picture: results) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/photoCard.fxml"));
            Parent root = loader.load();
            PictureCardController pcc = loader.getController();
            pcc.setPicture(picture);
            pcc.setMenuButtonDisable(true);

            // add the album card to flowpane
            resultsFlowPane.getChildren().add(root);

            // makes the two buttons visible
            clearResultsButton.setVisible(true);
            createAlbumButton.setVisible(true);
        }
        this.results.addAll(results);
    }

    @FXML
    public void clearResults() {
        resultsFlowPane.getChildren().clear();
        clearResultsButton.setVisible(false);
        createAlbumButton.setVisible(false);
        results.clear();
    }

    @FXML
    public void createAlbumWithResults() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/singleInputModal.fxml"));
        Parent root = loader.load();
        Stage modalStage = CreateStage.createModalStage();
        modalStage.setScene(CreateScene.createSingleInputModalScene(root));
        modalStage.show();

        SingleInputModalController simc = loader.getController();
        simc.setTitleText("Create Album With Results");
        simc.setMessageVisibility(false);
        simc.setInputLabelText("Album Name");
        simc.setConfirmButtonText("Add Album");
        simc.setConfirmButtonStyle(ButtonStyle.CONFIRM);
        simc.setConfirmButtonAction(e -> {
            UserList ul = new UserList();
            Preferences userPreferences = Preferences.userRoot();
            String loggedInUserId = userPreferences.get("sessionUser", "");
            Album newAlbum = new Album(simc.getInputText(), loggedInUserId);
            try {
                ul.getUser(loggedInUserId).addAlbum(newAlbum);

                // add the pictures to the album

                for (Picture picture: results) {
                    Picture copy = Picture.deepCopy(picture);
                    newAlbum.addPicture(copy);
                }

                simc.closeModal();
            } catch (IllegalArgumentException ex) {
                simc.setMessageVisibility(true);
                simc.setMessageStyle(TextStyle.DANGER);
                simc.setMessageText(ex.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
                simc.setMessageVisibility(true);
                simc.setMessageStyle(TextStyle.DANGER);
                simc.setMessageText("Unexpected error, please try again.");
            }
        });
    }


    private Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public void initialize() {
        headerController.setTitle("Album Name");
        headerController.setMenuButtonAction(e -> sidebarController.toggleVisibility());
        mainStackPane.setPickOnBounds(false);
        sidebarHBox.setPickOnBounds(false);
        results = new ArrayList<>();

    }



}
