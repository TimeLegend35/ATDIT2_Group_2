package de.badwalden.schule.ui.helper;

import de.badwalden.schule.dao.DBConnector;
import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.ui.views.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NavigationHelper {

    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());
    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;
    private final Stage primaryStage;
    private MainView mainView;


    public NavigationHelper(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Navigates to the specified view.
     * Only to be used when changing the whole window content, otherwise use setContentView()
     *
     * @param  viewName  the name of the view to navigate to
     */
    public void navigateTo(String viewName) {

        // Load the appropriate view based on viewName and parameters
        Scene scene = null;
        switch (viewName) {
            case "MainView":
                mainView = new MainView(); // Assuming constructor takes params
                scene = new Scene(mainView, WINDOW_WIDTH, WINDOW_HEIGHT);
                primaryStage.setTitle("Learning Hub - Bad Walden (Main View)");
                break;
            case "LoginView":
                LoginView loginView = new LoginView(); // Assuming constructor takes params
                scene = new Scene(loginView, WINDOW_WIDTH, WINDOW_HEIGHT);
                primaryStage.setTitle("Learning Hub - Bad Walden (Login View)");
                break;
            // Add other cases for different views
        }

        if (scene != null) {
            primaryStage.setScene(scene);
        }
    }

    /**
     * Sets the content view of the main stage based on the given view ID.
     *
     * @param  viewId  the ID of the view to navigate to
     */
    public void setContentView(String viewId) {
        logger.log(Level.INFO, "Navigating to content view with viewId: " + viewId);
        Node contentView = switch (viewId) {
            case "CareOfferMarketplace" -> new CareOfferMarketplaceView();

            // MOCKUPS
            case "Calandar" -> new ImageViewMockup("src/main/resources/images/Kalender Mockup.png");
            case "Grades" -> new ImageViewMockup("src/main/resources/images/Noten Mockup.png");
            case "SickNotes" -> new ImageViewMockup("src/main/resources/images/Krankmeldungen Mockup.png");
            case "Class" -> new ImageViewMockup("src/main/resources/images/Klasse Mockup.png");
            case "SchoolRegistration" -> new ImageViewMockup("src/main/resources/images/Schulanmeldung Mockup.png");
            case "SchoolCancellation" -> new ImageViewMockup("src/main/resources/images/Schulabmeldung Mockup.png");

            default -> {
                DialogHelper.showAlertDialog(Alert.AlertType.ERROR, "View not found", "View not found. If the Error persists, please contact the Administrator.");
                logger.log(Level.SEVERE, "View not found: " + viewId);
                yield new VBox(new Text("View not implemented."));
            }
        };
        mainView.setContentView(contentView);
    }

    /**
     * Sets the content view of the main stage based on the given object page.
     *
     * @param  object  the ID of the view to navigate to
     */
    public void setContentView(Object object) {
        logger.log(Level.INFO, "Navigating to Object Page of obj: " + object.toString());
        VBox contentView;

        if (object instanceof CareOffer) {
            contentView = new CareOfferView(); // Cast to CareOffer
        }

        // ... add else if blocks for other object types

        else {
            DialogHelper.showAlertDialog(Alert.AlertType.ERROR, "View not found", "View not found. If the Error persists, please contact the Administrator.");
            logger.log(Level.SEVERE, "Object page not found: " + object);
            contentView = new VBox(new Text("View not implemented."));
        }

        mainView.setContentView(contentView);
    }

}
