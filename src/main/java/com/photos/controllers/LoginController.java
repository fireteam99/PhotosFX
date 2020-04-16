package com.photos.controllers;

import com.photos.models.User;
import com.photos.models.UserList;
import com.photos.util.CreateScene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * LoginController class handles login
 * @author Robert Cheng, Ray Sy
 */
public class LoginController {
    @FXML
    private Label message;

    @FXML
    private TextField usernameBox;

    @FXML
    private TextField passwordBox;

    @FXML
    private Button submitCredButton;

    /**
     * handles the username and password entered in by the user
     * @param event ActionEvent
     * @throws IOException
     * @throws BackingStoreException
     */
    @FXML
    void submitCredentials(ActionEvent event) throws IOException, BackingStoreException {

        //get the user input
        String username = usernameBox.getText().trim();
        String password = passwordBox.getText().trim();

        UserList userList = new UserList();

        //check to see if user/pw combo exists in master user list
        //if user provides admin user/pw, go to admin page
        if (username.equals("admin")) {
            if (password.equals("admin")) {
                //System.out.println("admin successfully logged in!");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/admin.fxml"));
                Parent root = loader.load();
                Node n = (Node) event.getSource();
                Stage stage = (Stage) n.getScene().getWindow();
                Scene scene = CreateScene.createNormalScene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                message.setVisible(true);
                message.setText("Invalid password.");
            }
        } else if (userList.userExistsByUsername(username)) {
            User user = userList.getUserByUsername(username);
            if (passwordBox.getText().equals(user.getPassword())) {
                Preferences userPreferences = Preferences.userRoot();
                userPreferences.put("sessionUser", user.getId());
                userPreferences.flush();

                System.out.println(user.getId());

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/home.fxml"));
                Parent root = loader.load();
                Node n = (Node) event.getSource();
                Stage stage = (Stage) n.getScene().getWindow();
                Scene scene = new Scene(root, 1110, 750);
                stage.setScene(scene);
                stage.show();
            } else {
                message.setVisible(true);
                message.setText("Invalid password.");
            }

        } else {
            message.setVisible(true);
            message.setText("Invalid username.");
        }

    }

    public void initialize() {
        message.setVisible(false);
    }

}
