package de.badwalden.schule.ui.views;

import de.badwalden.schule.model.*;
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

public class CareOfferMarketplaceView extends ScrollPane {
    private CareOfferMarketplaceController controller;
    private VBox contentBox;
    private User user;

    public CareOfferMarketplaceView() {
        super(); // Adds spacing between child elements of the VBox
        controller = new CareOfferMarketplaceController();

        // check what user type is logged in and plot according
        user = Session.getInstance().getCurrentUser();

        contentBox = new VBox(15); // Adds spacing between child elements of the VBox
        contentBox.setPadding(new Insets(15)); // Set padding around the VBox container

        VBox registeredOffersBox = new VBox(15);
        VBox unregisteredOffersBox = new VBox(15);

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

            // The user should have two grouped lists: Registered and unregistered offers
            if (user instanceof Student) {
                if (((Student) user).isRegisteredForOffer(offer)) {
                    registeredOffersBox.getChildren().add(offerBox);
                } else {
                    unregisteredOffersBox.getChildren().add(offerBox);
                }
            } else {
                contentBox.getChildren().add(offerBox);
            }
        }

        // The user should have two grouped lists: Registered and unregistered offers
        if(user instanceof Student) {
            Label registeredLabel = new Label(LanguageHelper.getString("registered_offers"));
            registeredLabel.setFont(new Font(16));
            registeredLabel.setPadding(new Insets(10, 0, 0, 0));

            Label unregisteredLabel = new Label(LanguageHelper.getString("unregistered_offers"));
            unregisteredLabel.setFont(new Font(16));
            unregisteredLabel.setPadding(new Insets(10, 0, 0, 0));

            contentBox.getChildren().addAll(registeredLabel, registeredOffersBox, unregisteredLabel, unregisteredOffersBox);
        }

        // Set the VBox as the content of the ScrollPane
        this.setContent(contentBox);
        this.setFitToWidth(true); // Makes the ScrollPane fit the width of the contentBox, removing horizontal scroll bars if possible
    }

}
