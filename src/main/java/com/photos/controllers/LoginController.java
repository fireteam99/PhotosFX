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

public class LoginController {

    @FXML
    private TextField usernameBox;

    @FXML
    private TextField passwordBox;

    @FXML
    private Button submitCredButton;

    @FXML
    void submitCredentials(ActionEvent event) throws IOException {

        //get the user input
        String username = usernameBox.getText().trim();
        String password = passwordBox.getText().trim();

        //check to see if user/pw combo exists in master user list
        //if user provides admin user/pw, go to admin page
        if (username.equals("admin") && password.equals("admin")){
            //System.out.println("admin successfully logged in!");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"));
            Parent root = loader.load();
            Node n = (Node) event.getSource();
            Stage stage=(Stage) n.getScene().getWindow();
            Scene scene = new Scene(root, 750, 500);;
            stage.setScene(scene);
            stage.show();
        }
        else if (new UserList().userExists(username)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
            Parent root = loader.load();
            Node n = (Node) event.getSource();
            Stage stage=(Stage) n.getScene().getWindow();
            Scene scene = new Scene(root, 750, 500);;
            stage.setScene(scene);
            stage.show();
        }

    }



}
