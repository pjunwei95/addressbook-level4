package seedu.address.ui;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ClearConfirmation {
    @FXML
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation Dialog");
    alert.setHeaderText("Look, a Confirmation Dialog");
    alert.setContentText("Are you ok with this?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        // ... user chose OK
    } else {
        // ... user chose CANCEL or closed the dialog
    }
}
