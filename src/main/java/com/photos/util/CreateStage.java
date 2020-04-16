package com.photos.util;

import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * CreateStage class
 * @author Robert Cheng, Ray Sy
 */
public class CreateStage {
    public static Stage createModalStage() {
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initModality(Modality.WINDOW_MODAL);
        return stage;
    }
}
