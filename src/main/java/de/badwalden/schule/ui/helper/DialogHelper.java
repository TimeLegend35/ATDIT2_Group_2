package de.badwalden.schule.ui.helper;

import javafx.scene.control.Alert;
import javafx.scene.layout.Region;

public class DialogHelper {

    public DialogHelper() {

    }

    public static void showAlertDialog(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        // Set minimum width and height
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(300);

        alert.showAndWait();
    }

    
}
