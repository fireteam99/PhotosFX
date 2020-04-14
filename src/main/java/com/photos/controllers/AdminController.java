package com.photos.controllers;

import com.photos.models.User;
import com.photos.models.UserList;
import com.photos.util.ButtonStyle;
import com.photos.util.CreateScene;
import com.photos.util.CreateStage;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminController {
    @FXML
    private VBox usersContainer;

    @FXML
    private Button adminCreateUserButton;

    @FXML
    private Button logoutButton;

    @FXML
    void adminCreateUser(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/createUser.fxml"));
        Parent root = loader.load();
        Node n = (Node) event.getSource();
        Stage stage=(Stage) n.getScene().getWindow();
        Scene scene = new Scene(root, 750, 500);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void logout(ActionEvent event) throws IOException{
        goBack(event);
    }

    public void initialize() throws IOException {
        UserList u = new UserList();
        List<User> users = u.getUsers();
        refreshUsersList();
    }

    private void refreshUsersList() throws IOException {
        UserList userList = new UserList();
        // get updated list of users
        List<User> users = userList.getUsers();

        // remove all of the children
        usersContainer.getChildren().clear();

        // generate and add back in new children
        for (User user: users) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/userCard.fxml"));
            Parent root = loader.load();
            UserCardController ucc = loader.getController();
            ucc.setNameLabelText(user.getUsername());

            // set edit button to open up double input modal
            ucc.setEditButtonAction(e -> {
                System.out.println("editing user");
                FXMLLoader modalLoader = new FXMLLoader(getClass().getResource("/fxml/doubleInputModal.fxml"));
                try {
                    Parent modalRoot = modalLoader.load();
                    Stage modalStage = new CreateStage().createModalStage();
                    modalStage.setScene(CreateScene.createDoubleInputModalScene(modalRoot));
                    modalStage.show();

                    DoubleInputModalController dimc = modalLoader.getController();
                    dimc.setTitleText("Edit User");
                    dimc.setMessageVisibility(false);
                    dimc.setInputLabel1Text("Username");
                    dimc.setInput1Text(user.getUsername());
                    dimc.setInput2PromptText("Password");
                    dimc.setInput2Text(user.getPassword());
                    dimc.setConfirmButtonAction(event -> {
                        try {
                            // edit the user via userList
                            userList.editUser(user.getId(), dimc.getInput1Text(), dimc.getInput2Text());
                            // close the modal
                            dimc.closeModal();
                        } catch (IOException ex) {
                            // TODO: Show a modal on why it failed
                            ex.printStackTrace();
                        }
                    });
                    dimc.setConfirmButtonStyle(ButtonStyle.CONFIRM);
                } catch (IOException ex) {
                    // TODO: Show a modal on why it failed
                    ex.printStackTrace();
                }
            });
            // set delete button to open up confirmation modal
            ucc.setDeleteButtonAction(e -> System.out.println("deleting user"));
            usersContainer.getChildren().add(root);
        }
    }

    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = loader.load();
        Node n = (Node) event.getSource();
        Stage stage=(Stage) n.getScene().getWindow();
        Scene scene = CreateScene.createNormalScene(root);
        stage.setScene(scene);
        stage.show();
    }

}