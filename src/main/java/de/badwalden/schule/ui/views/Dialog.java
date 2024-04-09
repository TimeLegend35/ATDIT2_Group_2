package de.badwalden.schule.ui.views;

import javafx.scene.control.Alert;

public class Dialog {

    public Dialog() {

    }

    public static void showAlertDialog(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
