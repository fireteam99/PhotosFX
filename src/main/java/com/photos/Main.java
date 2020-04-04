package com.photos;
import com.photos.controllers.User;
import com.photos.controllers.UserList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));

        primaryStage.setTitle("Photos21");
        primaryStage.setScene(new Scene(root, 1000, 650));
        primaryStage.show();
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(400);

        //testing and debugging -- IGNORE
        UserList test = new UserList();
        test.setAdmin();
        User bob = new User("bob", "pass");
        test.addUser(bob);
        test.writeToSerFile(test);
        test.deserialize();

        System.out.println("List of users...");
        test.printUserList();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
