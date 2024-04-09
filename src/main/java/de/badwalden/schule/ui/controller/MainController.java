package de.badwalden.schule.ui.controller;

import de.badwalden.schule.ui.views.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class MainController {

    private MainView mainView;

    public MainController(MainView mainView) {
        this.mainView = mainView;
        attachSidebarEvents();
    }

    private void attachSidebarEvents() {
        for (Node node : mainView.getSidebarView().getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setOnAction(event -> handleSidebarNavigation(button.getId()));
            }
        }
    }

    private void handleSidebarNavigation(String viewId) {
        VBox contentView = switch (viewId) {
            case "Kalender" -> new KalenderView();
            case "Noten" -> new KalenderView();
            // ... more cases for other views
            default -> new VBox(new Text("View not implemented."));
        };
        mainView.setContentView(contentView);
    }
}
