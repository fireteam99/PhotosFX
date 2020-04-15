package com.photos.util;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class CreateScene {
    public static Scene createNormalScene(Parent root) {
        return new Scene(root, 1110, 750);
    }

    public static Scene createConfirmationModalScene(Parent root) {
        Scene scene = new Scene(root, 600, 350);
        scene.setFill(Color.TRANSPARENT);
        return scene;
    }

    public static Scene createSingleInputModalScene(Parent root) {
        Scene scene =  new Scene(root, 600, 420);
        scene.setFill(Color.TRANSPARENT);
        return scene;
    }

    public static Scene createDoubleInputModalScene(Parent root) {
        Scene scene = new Scene(root, 600, 500);
        scene.setFill(Color.TRANSPARENT);
        return scene;
    }

    public static Scene createComboBoxModalScene(Parent root) {
        Scene scene =  new Scene(root, 600, 420);
        scene.setFill(Color.TRANSPARENT);
        return scene;
    }
}
