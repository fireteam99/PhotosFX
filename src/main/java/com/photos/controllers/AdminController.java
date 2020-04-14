package com.photos.controllers;

import com.photos.models.User;
import com.photos.models.UserList;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdminController {
    @FXML
    private VBox usersContainer;

    private ObservableList<Node> userCardsList;

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

    public AdminController() {
        userCardsList = FXCollections.observableList(new ArrayList<>());
    }

    public void initialize() throws IOException {
        UserList u = new UserList();
        List<User> users = u.getUserList();

        for (User user: users) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/userCard.fxml"));
            Parent root = loader.load();
            UserCardController ucc = loader.getController();
            ucc.setNameLabelText(user.getUsername());
            ucc.setEditButtonAction(e -> System.out.println("editing user"));
            ucc.setDeleteButtonAction(e -> System.out.println("deleting user"));
            userCardsList.add(root);
            usersContainer.getChildren().add(root);
        }
    }

    private void refreshUsersList() {
        UserList userList = new UserList();
        List<User> users = userList.getUserList();
    }

    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent root = loader.load();
        Node n = (Node) event.getSource();
        Stage stage=(Stage) n.getScene().getWindow();
        Scene scene = new Scene(root, 750, 500);;
        stage.setScene(scene);
        stage.show();
    }

}