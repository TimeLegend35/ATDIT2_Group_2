package de.badwalden.schule.ui.helper;

import de.badwalden.schule.ui.views.*;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Stack;

public class NavigationHelper {

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
                scene = new Scene(mainView, 1280, 720);
                primaryStage.setTitle("Learning Hub - Bad Walden (Main View)");
                break;
            case "LoginView":
                LoginView loginView = new LoginView(); // Assuming constructor takes params
                scene = new Scene(loginView, 1280, 720);
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

    public void setContentView(String viewId) {
        VBox contentView = switch (viewId) {
            case "Kalender" -> new CalenderView();
            case "Noten" -> new CalenderView();
            case "Betreuungsmarktplatz" -> new CareOfferMarketplaceView(mainView);
            // ... more cases for other views
            default -> new VBox(new Text("View not implemented."));
        };
        mainView.setContentView(contentView);
    }

}
