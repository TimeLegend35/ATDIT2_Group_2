package de.badwalden.schule.ui.views;

import de.badwalden.schule.Main;
import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.ui.controller.CareOfferController;
import de.badwalden.schule.ui.controller.CareOfferMarketplaceController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class CareOfferView extends VBox {
    private CareOffer careOffer;
    private CareOfferController controller;

    private boolean isEditMode;

    public Label titleLabelValue;
    public TextField titleTextField;
    public Label descriptionLabelValue;
    public TextField descriptionTextField;
    public Label numberOfSeatsLabelValue;
    public TextField numberOfSeatsTextField;
    public Label youngestGradeLabelValue;
    public TextField youngestGradeTextField;

    public CareOfferView(CareOffer offer) {
        super(15); // Adds spacing between child elements of the VBox
        controller = new CareOfferController(this);
        this.careOffer = offer;

        // Set padding around the entire VBox container
        setPadding(new Insets(15));

        // Create a button to go back
        Button backButton = new Button("Zurück");
        backButton.setId("back"); // Set the button's ID to the offer's ID
        backButton.setOnAction(event -> Main.navigationHelper.setContentView("Betreuungsmarktplatz"));

        // Create a button to edit details
        Button editButton = new Button("Edit");
        editButton.setId(String.valueOf(offer.getId())); // Set the button's ID to the offer's ID
        editButton.setOnAction(event -> this.changeEditView(editButton));

        HBox topRightContainer = new HBox(backButton, editButton);
        topRightContainer.setAlignment(Pos.TOP_LEFT);
        topRightContainer.setPadding(new Insets(10));

        // Create labels for the offer's title and description
        Label titleLabel = new Label("Titel: ");
        titleLabel.setFont(new Font(14)); // Set font size for title
        titleLabelValue  = new Label();
        titleLabelValue.setFont(new Font(14)); // Set font size for title
        titleTextField = new TextField();
        titleTextField.setVisible(false);

        Label descriptionLabel = new Label("Beschreibung: ");
        descriptionLabel.setFont(new Font(14)); // Set font size for title
        descriptionLabelValue = new Label();
        descriptionLabelValue.setFont(new Font(14)); // Set font size for description
        descriptionLabelValue.setWrapText(true); // Allows the description to wrap within the label width
        descriptionTextField = new TextField();
        descriptionTextField.setVisible(false);

        Label numberOfSeatsLabel = new Label("Verfügbare Plätze: ");
        numberOfSeatsLabel.setFont(new Font(14)); // Set font size for title
        numberOfSeatsLabelValue = new Label();
        numberOfSeatsLabelValue.setFont(new Font(14)); // Set font size for description
        numberOfSeatsLabelValue.setWrapText(true); // Allows the description to wrap within the label width
        numberOfSeatsTextField = new TextField();
        numberOfSeatsTextField.setVisible(false);

        Label youngestGradeLabel = new Label("Jüngste Stufe: ");
        youngestGradeLabel.setFont(new Font(14)); // Set font size for title
        youngestGradeLabelValue = new Label();
        youngestGradeLabelValue.setFont(new Font(14)); // Set font size for description
        youngestGradeLabelValue.setWrapText(true); // Allows the description to wrap within the label width
        youngestGradeTextField = new TextField();
        youngestGradeTextField.setVisible(false);

        controller.updateValuesFromObject(careOffer);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.add(titleLabel, 0, 0);
        gridPane.add(titleLabelValue, 1, 0);
        gridPane.add(titleTextField, 1, 0);

        gridPane.add(descriptionLabel, 0, 1);
        gridPane.add(descriptionLabelValue, 1, 1);
        gridPane.add(descriptionTextField, 1, 1);

        gridPane.add(numberOfSeatsLabel, 0, 2);
        gridPane.add(numberOfSeatsLabelValue, 1, 2);
        gridPane.add(numberOfSeatsTextField, 1, 2);

        gridPane.add(youngestGradeLabel, 0, 3);
        gridPane.add(youngestGradeLabelValue, 1, 3);
        gridPane.add(youngestGradeTextField, 1, 3);

        // Create a button to register for this care offer
        Button registerButton = new Button("Anmelden");
        registerButton.setId(String.valueOf(offer.getId())); // Set the button's ID to the offer's ID
        registerButton.setOnAction(event -> System.out.println("Register button was pressed"));


        // Create a container for each offer's details and add them to the VBox
        VBox offerBox = new VBox(10); // Adds spacing between elements in each offer container
        offerBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(2))));
        offerBox.setPadding(new Insets(10)); // Add padding inside the border
        offerBox.getChildren().addAll(gridPane, registerButton);

        this.getChildren().addAll(topRightContainer, offerBox);

    }

    private void changeEditView(Button editButton) {
        if (!isEditMode) {
            isEditMode = true;

            toggleEditModeOfAttribute(titleLabelValue, titleTextField);
            toggleEditModeOfAttribute(descriptionLabelValue, descriptionTextField);
            toggleEditModeOfAttribute(numberOfSeatsLabelValue, numberOfSeatsTextField);
            toggleEditModeOfAttribute(youngestGradeLabelValue, youngestGradeTextField);

            editButton.setText("Save");
        } else {
            isEditMode = false;

            toggleEditModeOfAttribute(titleLabelValue, titleTextField);
            toggleEditModeOfAttribute(descriptionLabelValue, descriptionTextField);
            toggleEditModeOfAttribute(numberOfSeatsLabelValue, numberOfSeatsTextField);
            toggleEditModeOfAttribute(youngestGradeLabelValue, youngestGradeTextField);

            controller.setValuesOfObject(careOffer);
            controller.updateValuesFromObject(careOffer);
            editButton.setText("Edit");
        }
    }

    private void toggleEditModeOfAttribute(Node value, Node valueEditNode) {
        toggleVisibilityOfNode(value);
        toggleVisibilityOfNode(valueEditNode);
    }

    private void toggleVisibilityOfNode(Node node) {
        node.setVisible(!node.isVisible());
    }
}
