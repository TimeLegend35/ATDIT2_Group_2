package de.badwalden.schule.ui.controller;

import de.badwalden.schule.Main;
import de.badwalden.schule.ui.views.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
                button.setOnAction(event -> Main.navigationHelper.setContentView(button.getId()));
            }
        }
    }

    /**
    private void handleSidebarNavigation(String viewId) {
        VBox contentView = switch (viewId) {
            case "Kalender" -> new CalenderView();
            case "Noten" -> new CalenderView();
            case "Betreuungsmarktplatz" -> new CareOfferMarketplaceView(mainView);
            // ... more cases for other views
            default -> new VBox(new Text("View not implemented."));
        };
        mainView.setContentView(contentView);
    }
     **/
}
