package com.photos.util;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

/**
 * CreateScene class is a helper class to setup scenes - all static methods
 * @author Robert Cheng
 */
public class CreateScene {
    /**
     * createNormalScene used for creating main screens
     * @param root Parent
     * @return Scene
     */
    public static Scene createNormalScene(Parent root) {
        return new Scene(root, 1110, 750);
    }

    /**
     * used for creating confirmation modal screen popups
     * @param root Parent
     * @return Scene
     */
    public static Scene createConfirmationModalScene(Parent root) {
        Scene scene = new Scene(root, 600, 350);
        scene.setFill(Color.TRANSPARENT);
        return scene;
    }

    /**
     * used for creating single input modals
     * @param root Parent
     * @return Scene
     */
    public static Scene createSingleInputModalScene(Parent root) {
        Scene scene =  new Scene(root, 600, 420);
        scene.setFill(Color.TRANSPARENT);
        return scene;
    }

    /**
     * used for creating double input modals
     * @param root Parent
     * @return Scene
     */
    public static Scene createDoubleInputModalScene(Parent root) {
        Scene scene = new Scene(root, 600, 500);
        scene.setFill(Color.TRANSPARENT);
        return scene;
    }

    /**
     * used for creating combobox modals
     * @param root Parent
     * @return Scene
     */
    public static Scene createComboBoxModalScene(Parent root) {
        Scene scene =  new Scene(root, 600, 420);
        scene.setFill(Color.TRANSPARENT);
        return scene;
    }
}
