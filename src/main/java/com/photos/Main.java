package com.photos;
import com.photos.models.UserList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // normal screens
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/home.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/albumDetails.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/fxml/editPicture.fxml"));

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