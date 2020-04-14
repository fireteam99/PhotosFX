package com.photos.util;

import javafx.scene.Parent;
import javafx.scene.Scene;

public class CreateScene {
    public static Scene createNormalScene(Parent root) {
        return new Scene(root, 1110, 750);
    }

    public static Scene createConfirmationModalScene(Parent root) {
        return new Scene(root, 600, 350);
    }

    public static Scene createSingleInputModalScene(Parent root) {
        return new Scene(root, 600, 420);
    }

    public static Scene createDoubleInputModalScene(Parent root) {
        return new Scene(root, 600, 420);
    }
}
