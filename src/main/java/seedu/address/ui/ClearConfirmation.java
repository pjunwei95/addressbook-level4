package seedu.address.ui;

import java.util.Optional;
import java.util.logging.Logger;

import javax.swing.plaf.synth.Region;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

public class ClearConfirmation{
/*
    public static final String DEFAULT_PAGE = "default.html";
    private static final String ICON = "/images/address_book_32.png";
    private static final int MIN_HEIGHT = 600;
    private static final int MIN_WIDTH = 450;


    private MainWindow mainWindow;
    */

    private static final String FXML = "ClearConfirmation.fxml";
    private final Logger logger = LogsCenter.getLogger(this.getClass());

    public ClearConfirmation(){
        //super(FXML);
        // Set dependencies

        // Configure the UI
        //registerAsAnEventHandler(this);
    }
    public boolean isClearCommand() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        

        alert.setHeaderText(null);
        alert.setTitle("Confirmation Dialog");
        alert.setContentText("Are you sure you want to clear the entire Address Book?");


        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK
            return true;
        } else {
            // ... user chose CANCEL or closed the dialog
            return false;
        }
    }



}
