package sample.controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;

public class EditPictureController {

        @FXML
        private TitledPane editingPictureTitle;

        @FXML
        private TextField photoNameField;

        @FXML
        private TextArea photoCaptionField;

        @FXML
        private Text filePath;

        @FXML
        private Button cancelChangesButton;

        @FXML
        private Button saveChangesButton;

        @FXML
        void cancelPhotoEdit(ActionEvent event) {

        }

        @FXML
        void savePhotoEdit(ActionEvent event) {

        }

    }
