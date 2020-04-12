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
        // normal screens
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));

        primaryStage.setTitle("Photos21");
        primaryStage.setScene(new Scene(root, 1110, 700));
        primaryStage.show();
        primaryStage.setMinWidth(700);
        primaryStage.setMinHeight(400);

        // modals
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/confirmationModal.fxml"));
//        primaryStage.setTitle("Photos21");
//        primaryStage.initStyle(StageStyle.TRANSPARENT);
//        primaryStage.setScene(new Scene(root, 600, 350));
//        primaryStage.getScene().setFill(Color.TRANSPARENT);
//        primaryStage.show();
//        primaryStage.setMinWidth(500);
//        primaryStage.setMinHeight(200);


        //testing and debugging -- IGNORE
        UserList sess = new UserList();
        sess.setUpUsers();
        System.out.println("List of users...");
        sess.printUserList();

    }

    public static void main(String[] args) {
        launch(args);
    }
}