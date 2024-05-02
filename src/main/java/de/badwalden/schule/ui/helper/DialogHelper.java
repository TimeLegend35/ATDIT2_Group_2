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

    /**
     * Shows an alert dialog with the specified type, title, and content.
     *
     * @param type    the type of the alert
     * @param title   the title of the alert
     * @param content the content of the alert
     */
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

    /**
     * Shows a timed alert dialog with the specified type, title, and content. The dialog automatically closes after the specified time.
     *
     * @param type                 the type of the alert
     * @param title                the title of the alert
     * @param content              the content of the alert
     * @param displayTimeInSeconds the time in seconds before the dialog automatically closes
     */
    public static void showTimedAlertDialog(Alert.AlertType type, String title, String content,
                                            int displayTimeInSeconds) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);

        // Create an AtomicInteger for countdown
        AtomicInteger timeLeft = new AtomicInteger(displayTimeInSeconds);

        // Set content with a countdown label
        Label contentLabel = new Label(content + LanguageHelper.getString("closing_in") +
                timeLeft.get() + LanguageHelper.getString("seconds")
        );
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
                contentLabel.setText(content + LanguageHelper.getString("closing_in") +
                        timeLeft.get() + LanguageHelper.getString("seconds")
                );
            }
        }));
        timeline.play();

        alert.showAndWait();
    }


}
