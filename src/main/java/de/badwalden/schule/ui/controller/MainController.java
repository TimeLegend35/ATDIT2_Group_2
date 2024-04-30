package de.badwalden.schule.ui.controller;

import de.badwalden.schule.Main;
import de.badwalden.schule.ui.views.*;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class MainController {

    private MainView mainView;

    public MainController(MainView mainView) {
        this.mainView = mainView;
        attachSidebarEvents();
    }

    /**
     * Attaches events to the sidebar buttons.
     */
    private void attachSidebarEvents() {
        for (Node node : mainView.getSidebarView().getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                button.setOnAction(event -> Main.navigationHelper.setContentView(button.getId()));
            }
        }
    }
}
