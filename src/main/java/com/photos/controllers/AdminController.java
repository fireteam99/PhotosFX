package com.photos.controllers;

import com.photos.models.User;
import com.photos.models.UserList;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AdminController {

    @FXML
    private ListView<User> adminListView;

    private ObservableList<User> observableList;

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

    public void initialize() {
        UserList u = new UserList();
        ArrayList<User> users = u.getUserList();
        observableList = FXCollections.observableList(users);
        adminListView.setItems(observableList);
        adminListView.setCellFactory(adminListView -> new AdminListViewCellController());
    }

    public AdminController() {
        adminListView = new ListView<>();
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