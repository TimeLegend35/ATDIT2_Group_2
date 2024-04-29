package de.badwalden.schule.ui.helper;

import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.model.User;
import de.badwalden.schule.ui.views.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Stack;

// instantiated in Main class and used across multiple controllers for view navigation
public class NavigationHelper {

    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;
    private final Stage primaryStage;
    private final Stack<Scene> history = new Stack<>();
    private MainView mainView;


    public NavigationHelper(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void navigateTo(String viewName, Object... params) {
        // Before navigating, push the current scene to the history stack
        if (primaryStage.getScene() != null) {
            history.push(primaryStage.getScene());
        }

        // Load the appropriate view based on viewName and parameters
        Scene scene = null;
        switch(viewName) {
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

    public void navigateBack() {
        if (!history.isEmpty()) {
            Scene previousScene = history.pop();
            primaryStage.setScene(previousScene);
        }
    }

    // Handle navigation to sidebar main Views
    public void setContentView(String viewId) {
        System.out.println("Navigating to content view with viewId: " + viewId);
        Node contentView = switch (viewId) {
            case "Betreuungsmarktplatz" -> new CareOfferMarketplaceView();

            // MOCKUPS
            case "Kalender" -> new ImageViewMockup("src/main/resources/images/Kalender Mockup.png");
            case "Noten" -> new ImageViewMockup("src/main/resources/images/Noten Mockup.png");
            case "Krankmeldungen" -> new ImageViewMockup("src/main/resources/images/Krankmeldungen Mockup.png");
            case "Klasse" -> new ImageViewMockup("src/main/resources/images/Klasse Mockup.png");
            case "Schulanmeldung" -> new ImageViewMockup("src/main/resources/images/Schulanmeldung Mockup.png");
            case "Schulabmeldung" -> new ImageViewMockup("src/main/resources/images/Schulabmeldung Mockup.png");

            default -> new VBox(new Text("View not implemented."));
        };
        mainView.setContentView(contentView);
    }

    // Handle navigation if no String but an object is given to setContentView();
    public void setContentView(Object object) {
        System.out.println("Navigating to Object Page of obj: " + object.toString());
        VBox contentView;
        if (object instanceof CareOffer) {
            contentView = new CareOfferView(); // Cast to CareOffer
        }

        // ... add else if blocks for other object types

        else {
            contentView = new VBox(new Text("View not implemented or Object not found."));
        }
        mainView.setContentView(contentView);
    }

}
