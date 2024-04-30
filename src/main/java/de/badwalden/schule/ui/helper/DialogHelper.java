package de.badwalden.schule.ui.helper;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.util.Duration;

import java.util.concurrent.atomic.AtomicInteger;


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

    public static void showTimedAlertDialog(Alert.AlertType type, String title, String content, int displayTimeInSeconds) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);

        // Create an AtomicInteger for countdown
        AtomicInteger timeLeft = new AtomicInteger(displayTimeInSeconds);

        // Set content with a countdown label
        Label contentLabel = new Label(content + "\nClosing in " + timeLeft.get() + " seconds.");
        alert.getDialogPane().setContent(contentLabel);

        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.getDialogPane().setMinWidth(300);

        // Countdown timer to update label and close dialog
        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), event -> {
            if (timeLeft.decrementAndGet() <= 0) {
                alert.close();
                timeline.stop();
            } else {
                contentLabel.setText(content + "\nClosing in " + timeLeft.get() + " seconds.");
            }
        }));
        timeline.play();

        alert.showAndWait();
    }

    
}
