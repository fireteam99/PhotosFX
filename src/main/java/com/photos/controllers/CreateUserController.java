package com.photos.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateUserController {

    @FXML
    private TextField newUserBox;

    @FXML
    private TextField newPasswordBox;

    @FXML
    private Button submitNewUserButton;

    @FXML
    private Button cancelNewUserButton;

    @FXML
    private Button closeButton;

    @FXML
    void cancelCreateNewUser(ActionEvent event) throws IOException {
        goBack(event);
    }

    @FXML
    void closeCreateNewUser(ActionEvent event) throws IOException {
        goBack(event);
    }

    @FXML
    void submitNewUser(ActionEvent event) throws IOException {
        String username = newUserBox.getText();
        String password = newPasswordBox.getText();
        User newUser = new User(username,password);
        UserList uL = new UserList();
        uL.addUser(newUser); //add to userList

        //go back to admin page
        goBack(event);
    }

    private void goBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"));
        Parent root = loader.load();
        Node n = (Node) event.getSource();
        Stage stage=(Stage) n.getScene().getWindow();
        Scene scene = new Scene(root, 750, 500);;
        stage.setScene(scene);
        stage.show();
    }

}
