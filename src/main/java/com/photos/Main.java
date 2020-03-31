package com.photos;
import com.photos.controllers.UserList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
        primaryStage.setTitle("Photos21");
        primaryStage.setScene(new Scene(root, 1000, 650));
        primaryStage.show();
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(550);

        UserList test = new UserList();
        test.setAdmin();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
