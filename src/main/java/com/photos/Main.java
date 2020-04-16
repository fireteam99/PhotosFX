package com.photos;
import com.photos.models.UserList;
import com.photos.util.CreateScene;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.prefs.Preferences;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // normal screens
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/albumDetails.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/editPicture.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/search.fxml"));


        primaryStage.setTitle("Photos21");
        primaryStage.setScene(new Scene(root, 1110, 750));
        primaryStage.show();
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(400);

        // confirmation modal
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/confirmationModal.fxml"));
//        primaryStage.setTitle("Photos21");
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        primaryStage.setScene(new Scene(root, 600, 350));
//        primaryStage.getScene().setFill(Color.TRANSPARENT);
//        primaryStage.show();
//        primaryStage.setMinWidth(600);
//        primaryStage.setMinHeight(350);

        // single input modal
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/singleInputModal.fxml"));
//        primaryStage.setTitle("Photos21");
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        primaryStage.setScene(new Scene(root, 600, 420));
//        primaryStage.getScene().setFill(Color.TRANSPARENT);
//        primaryStage.show();
//        primaryStage.setMinWidth(600);
//        primaryStage.setMinHeight(420);

        // double input modal
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/doubleInputModal.fxml"));
//        primaryStage.setTitle("Photos21");
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        primaryStage.setScene(new Scene(root, 600, 420));
//        primaryStage.getScene().setFill(Color.TRANSPARENT);
//        primaryStage.show();
//        primaryStage.setMinWidth(600);
//        primaryStage.setMinHeight(500);

        // combo box modal
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/comboBoxModal.fxml"));
//        primaryStage.setTitle("Photos21");
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        primaryStage.setScene(CreateScene.createComboBoxModalScene(root));
//        primaryStage.getScene().setFill(Color.TRANSPARENT);
//        primaryStage.show();
//        primaryStage.setMinWidth(600);
//        primaryStage.setMinHeight(420);


        // clears all preferences for a clean start
        Preferences userPreferences = Preferences.userRoot();
        userPreferences.clear();

        // to skip login for testing
//        userPreferences.put("sessionUser", "af1eaaea-27c3-43a9-9d9f-680cfeb9be32");
//        userPreferences.flush();

        UserList sess = new UserList();
        sess.setUpUsers();

        //testing and debugging -- IGNORE
//        UserList sess = new UserList();
//        System.out.println("List of users...");
//        sess.printUserList();

    }

    public static void main(String[] args) {
        launch(args);
    }
}