package de.badwalden.schule.ui.views;

import de.badwalden.schule.Main;
import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.ui.controller.CareOfferController;
import de.badwalden.schule.ui.controller.CareOfferMarketplaceController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CareOfferView extends VBox {
    private CareOffer careOffer;
    private CareOfferController controller;
    public CareOfferView(CareOffer offer) {
        super(15); // Adds spacing between child elements of the VBox
        controller = new CareOfferController(this);

        // Set padding around the entire VBox container
        setPadding(new Insets(15));

        // Create a button to go back
        Button backButton = new Button("Zurück");
        backButton.setId("back"); // Set the button's ID to the offer's ID
        backButton.setOnAction(event -> Main.navigationHelper.setContentView("Betreuungsmarktplatz"));

        HBox topRightContainer = new HBox(backButton);
        topRightContainer.setAlignment(Pos.TOP_LEFT);
        topRightContainer.setPadding(new Insets(10));

        // Create labels for the offer's title and description
        Label titleLabel = new Label(offer.getName());
        titleLabel.setFont(new Font(18)); // Set font size for title

        Label descriptionLabel = new Label(offer.getDescription());
        descriptionLabel.setFont(new Font(14)); // Set font size for description
        descriptionLabel.setWrapText(true); // Allows the description to wrap within the label width

        Label numberOfSeatsLabel = new Label("Seats: " + String.valueOf(offer.getNumberOfSeats()));
        numberOfSeatsLabel.setFont(new Font(14)); // Set font size for description
        numberOfSeatsLabel.setWrapText(true); // Allows the description to wrap within the label width

        // Create a button to view details
        Button detailsButton = new Button("View Details");
        detailsButton.setId(String.valueOf(offer.getId())); // Set the button's ID to the offer's ID


        // Create a container for each offer's details and add them to the VBox
        VBox offerBox = new VBox(10); // Adds spacing between elements in each offer container
        offerBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(2))));
        offerBox.setPadding(new Insets(10)); // Add padding inside the border
        offerBox.getChildren().addAll(titleLabel, descriptionLabel, numberOfSeatsLabel, detailsButton);

        this.getChildren().addAll(topRightContainer, offerBox);

    }
}
