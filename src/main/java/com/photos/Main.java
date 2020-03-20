package com.photos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        primaryStage.setTitle("Photos");
        primaryStage.setScene(new Scene(root, 1000, 650));
        primaryStage.show();
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(400);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
