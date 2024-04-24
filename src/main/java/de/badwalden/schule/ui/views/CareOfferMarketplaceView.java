package de.badwalden.schule.ui.views;

import de.badwalden.schule.Main;
import de.badwalden.schule.model.Admin;
import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.model.Parent;
import de.badwalden.schule.model.User;
import de.badwalden.schule.ui.controller.CareOfferMarketplaceController;
import de.badwalden.schule.ui.helper.LanguageHelper;
import de.badwalden.schule.ui.helper.Session;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.List;

public class CareOfferMarketplaceView extends ScrollPane {
    private CareOfferMarketplaceController controller;
    private VBox contentBox;

    public CareOfferMarketplaceView() {
        super(); // Adds spacing between child elements of the VBox
        controller = new CareOfferMarketplaceController();

        // check what user type is logged in and plot according
        User user = Session.getInstance().getCurrentUser();

        if (user instanceof Admin || user instanceof Parent) {

            contentBox = new VBox(15); // Adds spacing between child elements of the VBox
            contentBox.setPadding(new Insets(15)); // Set padding around the VBox container

            for (Object careOfferObject : controller.getData()) {
                CareOffer offer = (CareOffer) careOfferObject;
                // Create labels for the offer's title and description
                Label titleLabel = new Label(offer.getName());
                titleLabel.setFont(new Font(18)); // Set font size for title

                Label descriptionLabel = new Label(offer.getDescription());
                descriptionLabel.setFont(new Font(14)); // Set font size for description
                descriptionLabel.setWrapText(true); // Allows the description to wrap within the label width

                // Create a button to view details
                Button detailsButton = new Button(LanguageHelper.getString("view_details"));
                detailsButton.setId(String.valueOf(offer.getId())); // Set the button's ID to the offer's ID
                detailsButton.setOnAction(event -> controller.showObjectPage(offer));


                // Create a container for each offer's details and add them to the VBox
                VBox offerBox = new VBox(10); // Adds spacing between elements in each offer container
                offerBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                        CornerRadii.EMPTY, new BorderWidths(2))));
                offerBox.setPadding(new Insets(10)); // Add padding inside the border
                offerBox.getChildren().addAll(titleLabel, descriptionLabel, detailsButton);

                contentBox.getChildren().add(offerBox);
            }

            // Set the VBox as the content of the ScrollPane
            this.setContent(contentBox);
            this.setFitToWidth(true); // Optional: Makes the ScrollPane fit the width of the contentBox, removing horizontal scroll bars if possible
        }
    }

}
