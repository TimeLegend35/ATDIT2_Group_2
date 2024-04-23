package de.badwalden.schule.ui.views;

import de.badwalden.schule.Main;
import de.badwalden.schule.model.*;
import de.badwalden.schule.ui.controller.CareOfferController;
import de.badwalden.schule.ui.controller.CareOfferMarketplaceController;
import de.badwalden.schule.ui.helper.DialogHelper;
import de.badwalden.schule.ui.helper.LanguageHelper;
import de.badwalden.schule.ui.helper.Session;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static de.badwalden.schule.Main.navigationHelper;

public class CareOfferView extends VBox {
    private CareOffer careOffer;
    private CareOfferController controller;

    private static final int FONT_SIZE = 14;
    private boolean isEditMode;
    public ObservableList<ObjectPageAttributeElementsContainer> uiElements = FXCollections.observableArrayList();

    public Label titleLabelValue;
    public TextField titleTextField;
    public Label descriptionLabelValue;
    public TextField descriptionTextField;
    public Label numberOfSeatsLabelValue;
    public TextField numberOfSeatsTextField;
    public Label youngestGradeLabelValue;
    public TextField youngestGradeTextField;
    public Label oldestGradeLabelValue;
    public TextField oldestGradeTextField;

    public CareOfferView() {
        super(15); // Adds spacing between child elements of the VBox
        controller = new CareOfferController(this);
        careOffer = (CareOffer) controller.getData()[0];

        // check what user type is logged in and plot according
        User user = Session.getInstance().getCurrentUser();

        // Set padding around the entire VBox container
        setPadding(new Insets(15));

        // Create a button to go back
        Button backButton = new Button(LanguageHelper.getString("return_button"));
        backButton.setId("back"); // Set the button's ID to the offer's ID
        backButton.setOnAction(event -> navigationHelper.setContentView("Betreuungsmarktplatz"));

        HBox topRightContainer = new HBox(backButton);
        topRightContainer.setAlignment(Pos.TOP_LEFT);
        topRightContainer.setPadding(new Insets(10));

        // Only the Admin and the Main Supervisor should be able to edit Care Offers
        if (user.getId() == careOffer.getMainSupervisor().getId() || user instanceof Admin) {
            // Create a button to edit details
            Button editButton = new Button(LanguageHelper.getString("edit_button"));
            editButton.setId(String.valueOf(careOffer.getId())); // Set the button's ID to the offer's ID
            editButton.setOnAction(event -> this.changeEditView(editButton));
            topRightContainer.getChildren().add(editButton);
        }

        instantiateAttributes();

        controller.updateValuesFromObject(careOffer);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        addAllAttributesToGridPane(gridPane);

        // Create a button to register for this care offer
        Button registerButton = new Button(LanguageHelper.getString("sign_up_child"));
        registerButton.setId(String.valueOf(careOffer.getId())); // Set the button's ID to the offer's ID
        registerButton.setOnAction(event -> {this.openRegistrationDialog(Session.getInstance().getCurrentUser());});

        // Create a container for each offer's details and add them to the VBox
        VBox offerBox = new VBox(10); // Adds spacing between elements in each offer container
        offerBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(2))));
        offerBox.setPadding(new Insets(10)); // Add padding inside the border
        offerBox.getChildren().addAll(gridPane, registerButton);

        this.getChildren().addAll(topRightContainer, offerBox);
    }

    /**
     * Opens a registration dialog for a care offer based on the number of children a parent has.
     *
     * @param  user   the User initiating the registration
     */
    private void openRegistrationDialog(User user) {
        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle(LanguageHelper.getString("sign_up_child"));
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        if (user instanceof Parent) {
            Parent parent = (Parent) user;
            if (parent.getChildren().size() > 1) {
                openDialogForMultipleChildren(dialog, parent);
            } else if (parent.getChildren().size() == 1) {
                openDialogForSingleChild(dialog, parent);
            } else {
                showNoChildrenAlert();
            }
        }

    }


    private void openDialogForMultipleChildren(Dialog<User> dialog, Parent parent) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        int row = 0;
        for (Student child : parent.getChildren()) {
            Label childNameLabel = new Label(child.getFirstName() + " ");
            Button dialogRegistrationButton = new Button();

            if(controller.isChildRegisteredForOffer(child)) {
                dialogRegistrationButton.setText(LanguageHelper.getString("remove_child"));
            } else {
                dialogRegistrationButton.setText(LanguageHelper.getString("add_child"));
            }

            dialogRegistrationButton.setOnAction(event -> {
                controller.changeCareOfferAttendance(careOffer, child, dialogRegistrationButton);
            });

            grid.add(childNameLabel, 0, row);
            grid.add(dialogRegistrationButton, 1, row);
            row++;
        }

        dialog.getDialogPane().setContent(grid);
        dialog.showAndWait();
    }



    private void openDialogForSingleChild(Dialog<User> dialog, Parent parent) {
        Student child = parent.getChildren().get(0);
        String registration = LanguageHelper.getString("registration");
        registration= registration.replace("{child_name}", child.getFirstName());
        Label registrationPrompt = new Label(registration);

        GridPane grid = new GridPane();
        grid.add(registrationPrompt, 0, 0);
        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == ButtonType.OK) {
                // Call the method to change the attendance when OK is pressed.
                controller.changeCareOfferAttendance(careOffer, child);
                return child; // Return the child as the result if OK is pressed.
            }
            return null; // Return null if the dialog is canceled or closed without confirmation.
        });
        dialog.showAndWait();
    }

    /**
     * Displays an alert dialog indicating that there are no children connected to the current user.
     */
    private void showNoChildrenAlert() {
        DialogHelper.showAlertDialog(Alert.AlertType.ERROR, LanguageHelper.getString("sign_up_child"), LanguageHelper.getString("no_child_alert"));
    }


    /**
     * Instantiates the attributes for the offer, including labels and text fields for each attribute.
     * Sets the font size for each label and text field, and adds the attributes to the uiElements list.
     */
    public void instantiateAttributes() {
        // Create labels for the offer's title and description
        Label titleLabel = new Label(LanguageHelper.getString("title"));
        titleLabel.setFont(new Font(FONT_SIZE)); // Set font size for title
        titleLabelValue = new Label();
        titleLabelValue.setFont(new Font(FONT_SIZE)); // Set font size for title
        titleTextField = new TextField();
        titleTextField.setVisible(false);
        uiElements.add(new ObjectPageAttributeElementsContainer(titleLabel, titleLabelValue, titleTextField));

        Label descriptionLabel = new Label(LanguageHelper.getString("description"));
        descriptionLabel.setFont(new Font(FONT_SIZE)); // Set font size for title
        descriptionLabelValue = new Label();
        descriptionLabelValue.setFont(new Font(FONT_SIZE)); // Set font size for description
        descriptionLabelValue.setWrapText(true); // Allows the description to wrap within the label width
        descriptionTextField = new TextField();
        descriptionTextField.setVisible(false);
        uiElements.add(new ObjectPageAttributeElementsContainer(descriptionLabel, descriptionLabelValue, descriptionTextField));

        Label numberOfSeatsLabel = new Label(LanguageHelper.getString("open_seats"));
        numberOfSeatsLabel.setFont(new Font(FONT_SIZE)); // Set font size for title
        numberOfSeatsLabelValue = new Label();
        numberOfSeatsLabelValue.setFont(new Font(FONT_SIZE)); // Set font size for description
        numberOfSeatsLabelValue.setWrapText(true); // Allows the description to wrap within the label width
        numberOfSeatsTextField = new TextField();
        numberOfSeatsTextField.setVisible(false);
        uiElements.add(new ObjectPageAttributeElementsContainer(numberOfSeatsLabel, numberOfSeatsLabelValue, numberOfSeatsTextField));

        Label youngestGradeLabel = new Label(LanguageHelper.getString("youngest_class"));
        youngestGradeLabel.setFont(new Font(FONT_SIZE)); // Set font size for title
        youngestGradeLabelValue = new Label();
        youngestGradeLabelValue.setFont(new Font(FONT_SIZE)); // Set font size for description
        youngestGradeLabelValue.setWrapText(true); // Allows the description to wrap within the label width
        youngestGradeTextField = new TextField();
        youngestGradeTextField.setVisible(false);
        uiElements.add(new ObjectPageAttributeElementsContainer(youngestGradeLabel, youngestGradeLabelValue, youngestGradeTextField));

        Label oldestGradeLabel = new Label(LanguageHelper.getString("oldest_class"));
        oldestGradeLabel.setFont(new Font(FONT_SIZE)); // Set font size for title
        oldestGradeLabelValue = new Label();
        oldestGradeLabelValue.setFont(new Font(FONT_SIZE)); // Set font size for description
        oldestGradeLabelValue.setWrapText(true); // Allows the description to wrap within the label width
        oldestGradeTextField = new TextField();
        oldestGradeTextField.setVisible(false);
        uiElements.add(new ObjectPageAttributeElementsContainer(oldestGradeLabel, oldestGradeLabelValue, oldestGradeTextField));
    }



    /**
     * Represents a container for elements related to an object page attribute.
     * This class encapsulates the title label and UI nodes for both initial and edit modes.
     */
    public class ObjectPageAttributeElementsContainer {

        /**
         * Constructs a new ObjectPageAttributeElementsContainer with the provided elements.
         *
         * @param titleLabel     The label representing the title of the attribute.
         * @param initialUiNode  The UI node representing the attribute in initial mode.
         * @param editModeUiNode The UI node representing the attribute in edit mode.
         */
        public ObjectPageAttributeElementsContainer(Label titleLabel, Node initialUiNode, Node editModeUiNode) {
            this.titleLabel = titleLabel;
            this.initialUiNode = initialUiNode;
            this.editModeUiNode = editModeUiNode;
        }

        public Label titleLabel;
        public Node initialUiNode;
        public Node editModeUiNode;
    }

    /**
     * Add all attributes to the provided GridPane.
     *
     * @param gridPane the GridPane to which attributes will be added
     */
    private void addAllAttributesToGridPane(GridPane gridPane) {
        for (ObjectPageAttributeElementsContainer container : uiElements) {
            addAttributeToGridPane(gridPane, container.titleLabel, container.initialUiNode, container.editModeUiNode);
        }
    }

    /**
     * Adds a Single attribute to a GridPane.
     *
     * @param gridPane       the GridPane to which the attribute will be added
     * @param label          the label for the attribute
     * @param initialUiNode  the initial UI node for the attribute
     * @param editModeUiNode the edit mode UI node for the attribute
     */
    private void addAttributeToGridPane(GridPane gridPane, Label label, Node initialUiNode, Node editModeUiNode) {
        int rowCount = gridPane.getRowCount();
        gridPane.add(label, 0, rowCount);
        gridPane.add(initialUiNode, 1, rowCount);
        gridPane.add(editModeUiNode, 1, rowCount);
    }

    /**
     * Changes the edit view based on the current mode.
     *
     * @param editButton the Button used to toggle edit mode
     */
    private void changeEditView(Button editButton) {
        if (!isEditMode) {
            isEditMode = true;

            for (ObjectPageAttributeElementsContainer container : uiElements) {
                toggleEditModeOfAttribute(container.initialUiNode, container.editModeUiNode);
            }

            editButton.setText("Save");
        } else {
            isEditMode = false;

            for (ObjectPageAttributeElementsContainer container : uiElements) {
                toggleEditModeOfAttribute(container.initialUiNode, container.editModeUiNode);
            }

            controller.setValuesOfObject(careOffer);
            controller.updateValuesFromObject(careOffer);
            editButton.setText("Edit");
        }
    }

    /**
     * Toggles the edit mode of an attribute by toggling the visibility of the attribute node and its edit node.
     *
     * @param value         the attribute node
     * @param valueEditNode the edit node of the attribute
     */
    private void toggleEditModeOfAttribute(Node value, Node valueEditNode) {
        toggleVisibilityOfNode(value);
        toggleVisibilityOfNode(valueEditNode);
    }

    /**
     * Toggles the visibility of a given node.
     *
     * @param node the node to toggle visibility
     */
    private void toggleVisibilityOfNode(Node node) {
        node.setVisible(!node.isVisible());
    }
}
