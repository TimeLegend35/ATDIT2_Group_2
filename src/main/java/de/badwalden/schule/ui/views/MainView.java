package de.badwalden.schule.ui.views;

import de.badwalden.schule.ui.controller.MainController;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainView extends BorderPane {

    private SidebarView sidebarView;
    private SplitPane mainSplitPane;
    private MainController mainController;

    public MainView() {
        sidebarView = new SidebarView();
        mainSplitPane = new SplitPane();
        mainController = new MainController(this);

        // Place the sidebar on the left side of the mainSplitPane
        mainSplitPane.getItems().add(sidebarView);

        // Set the initial position of the divider (adjust as needed)
        mainSplitPane.setDividerPosition(0, 0.3); // 30% of the total width for the sidebar

        // The rest of the mainSplitPane will contain the dynamic content views
        mainSplitPane.getItems().add(new ImageViewMockup("src/main/resources/images/BadWaldenHomePage.png")); // Placeholder for content views
        setCenter(mainSplitPane);
    }

    /**
     * Returns the SidebarView object that represents the sidebar view in the main view.
     *
     * @return the SidebarView object representing the sidebar view
     */
    public SidebarView getSidebarView() {
        return sidebarView;
    }

    /**
     * Sets the content view in the mainSplitPane based on the number of existing items.
     *
     * @param  contentView   the Node to set as the content view
     */
    public void setContentView(Node contentView) {
        if (mainSplitPane.getItems().size() > 1) {
            mainSplitPane.getItems().set(1, contentView);
        } else {
            mainSplitPane.getItems().add(contentView);
        }
    }
}
