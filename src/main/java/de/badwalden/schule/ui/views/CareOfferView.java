package de.badwalden.schule.ui.views;

import de.badwalden.schule.model.*;
import de.badwalden.schule.ui.controller.CareOfferController;
import de.badwalden.schule.ui.helper.DialogHelper;
import de.badwalden.schule.ui.helper.LanguageHelper;
import de.badwalden.schule.ui.helper.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import static de.badwalden.schule.Main.navigationHelper;

public class CareOfferView extends VBox {
    private CareOffer careOffer;
    private CareOfferController controller;

    private static final int FONT_SIZE = 14;
    public ObservableList<ObjectPageAttributeElementsContainer> uiElements = FXCollections.observableArrayList();

    public Label titleLabelValue;
    public TextField titleTextField;
    public Label descriptionLabelValue;
    public TextField descriptionTextField;
    public Label seatsAvailableLabelValue;
    public TextField seatsAvailableTextField;
    public Label youngestGradeLabelValue;
    public TextField youngestGradeTextField;
    public Label oldestGradeLabelValue;
    public TextField oldestGradeTextField;
    private User user ;

    public CareOfferView() {
        super(15); // Adds spacing between child elements of the VBox
        setPadding(new Insets(15)); // Set padding around the entire VBox container

        // Get the Controller and set the current user
        controller = new CareOfferController(this);
        careOffer = (CareOffer) controller.getData()[0];
        user = Session.getInstance().getCurrentUser();

        // Create the top bar
        HBox topRightContainer = createTopContainer();

        // Instantiate the CareOffer attributes
        instantiateAttributes();

        // Pull the data and set the values in the UI
        controller.updateValuesFromObject(careOffer);

        // Create the main grid pane to display the attributes
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add all instantiated attributes to the grid pane
        addAllAttributesToGridPane(gridPane);

        // Create the container for the offer's details and add them to the VBox with spacing and padding
        VBox offerBox = new VBox(10);
        offerBox.setPadding(new Insets(10));
        offerBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                CornerRadii.EMPTY, new BorderWidths(2))));

        // Add everything to the container of the offer's details
        offerBox.getChildren().add(gridPane);

        if (user instanceof Parent) {
            // Create a button to register for this care offer
            Button registerButton = createRegisterButton();
            offerBox.getChildren().add(registerButton);
        }

        this.getChildren().addAll(topRightContainer, offerBox);
    }

    /**
     * Creates and returns a top navigation container with back and edit buttons.
     *
     * @return HBox containing navigation buttons.
     */
    private HBox createTopContainer() {
        Button backButton = new Button(LanguageHelper.getString("return"));
        backButton.setId("back");
        backButton.setOnAction(event -> navigationHelper.setContentView("CareOfferMarketplace"));

        HBox topRightContainer = new HBox(backButton);
        topRightContainer.setAlignment(Pos.TOP_LEFT);
        topRightContainer.setPadding(new Insets(10));
        addEditButtonIfNeeded(topRightContainer);

        return topRightContainer;
    }

    /**
     * Adds an edit button to the top container if the user is authorized.
     *
     * @param container The container to which the edit button is added.
     */
    private void addEditButtonIfNeeded(HBox container) {
        if (user.getId() == careOffer.getMainSupervisor().getId()) {
            Button editButton = new Button(LanguageHelper.getString("edit"));
            editButton.setId(String.valueOf(careOffer.getId()));
//            editButton.setOnAction(event -> this.changeEditView(editButton));
            container.getChildren().add(editButton);
        }
    }

    /**
     * Instantiates and returns a registration button for the care offer.
     *
     * @return Button for registration.
     */
    private Button createRegisterButton() {
        Button registerButton = new Button(LanguageHelper.getString("sign_up_child"));
        registerButton.setId(String.valueOf(careOffer.getId())); // Set the button's ID to the offer's ID
        registerButton.setOnAction(event -> {
            this.openRegistrationDialog(Session.getInstance().getCurrentUser());
        });
        return registerButton;
    }

    /**
     * Opens a registration dialog for a care offer based on the number of children a parent has.
     *
     * @param user the User initiating the registration
     */
    private void openRegistrationDialog(User user) {
        Dialog<User> dialog = new Dialog<>();
        dialog.setTitle(LanguageHelper.getString("sign_up_child"));
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        if (user instanceof Parent parent) {
            if (parent.getChildren().size() >= 1) {
                addContentForDialog(dialog, parent);
            }
        } else {
            showNoChildrenAlert();
        }

    }

    /**
     * Opens a dialog for a parent with multiple children.
     *
     * @param  dialog   The dialog to be displayed
     * @param  parent   The parent object containing children
     */
    private void addContentForDialog(Dialog<User> dialog, Parent parent) {
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        int row = 0;
        for (Student child : parent.getChildren()) {
            if (controller.isRightOfSerice(careOffer, child)) {
                Label childNameLabel = new Label(child.getFirstName() + " (" + LanguageHelper.getString("current_class") + " " + child.getClassYear() + ")");
                Button dialogRegistrationButton = new Button();

                if (child.isRegisteredForOffer(careOffer)) {
                    dialogRegistrationButton.setText(LanguageHelper.getString("remove_child"));
                } else {
                    dialogRegistrationButton.setText(LanguageHelper.getString("add_child"));
                }

                dialogRegistrationButton.setOnAction(event -> {
                    controller.changeCareOfferRegistration(careOffer, child, dialogRegistrationButton);
                });

                grid.add(childNameLabel, 0, row);
                grid.add(dialogRegistrationButton, 1, row);
                row++;

            } else {

                // If the child has no right of service, just show the label and the class year
                Label childNameLabel = new Label(child.getFirstName() + " (" + LanguageHelper.getString("current_class") + " " + child.getClassYear() + ")");
                grid.add(childNameLabel, 0, row);

                // Add a button if the child is registered but has no right of service
                if (child.isRegisteredForOffer(careOffer)) {
                    Button unregisterChildNoRightOfService = new Button(LanguageHelper.getString("remove_child"));
                    unregisterChildNoRightOfService.setOnAction(event -> {
                        controller.changeCareOfferRegistration(careOffer, child, unregisterChildNoRightOfService);
                        unregisterChildNoRightOfService.setVisible(false);
                    });
                    grid.add(unregisterChildNoRightOfService, 1, row);
                }

                row++;
            }

        }

        dialog.getDialogPane().setContent(grid);
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
        seatsAvailableLabelValue = new Label();
        seatsAvailableLabelValue.setFont(new Font(FONT_SIZE)); // Set font size for description
        seatsAvailableLabelValue.setWrapText(true); // Allows the description to wrap within the label width
        seatsAvailableTextField = new TextField();
        seatsAvailableTextField.setVisible(false);
        uiElements.add(new ObjectPageAttributeElementsContainer(numberOfSeatsLabel, seatsAvailableLabelValue, seatsAvailableTextField));

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

//    /**
//     * Changes the edit view based on the current mode.
//     *
//     * @param editButton the Button used to toggle edit mode
//     */
//    private void changeEditView(Button editButton) {
//        DialogHelper.showAlertDialog(Alert.AlertType.INFORMATION, LanguageHelper.getString("sign_up_child"), LanguageHelper.getString("edit_functionality_not_implemented"));
//        if (!isEditMode) {
//            isEditMode = true;
//
//            for (ObjectPageAttributeElementsContainer container : uiElements) {
//                toggleEditModeOfAttribute(container.initialUiNode, container.editModeUiNode);
//            }
//
//            editButton.setText("Save");
//        } else {
//            isEditMode = false;
//
//            for (ObjectPageAttributeElementsContainer container : uiElements) {
//                toggleEditModeOfAttribute(container.initialUiNode, container.editModeUiNode);
//            }
//
//            controller.setValuesOfObject(careOffer);
//            controller.updateValuesFromObject(careOffer);
//            editButton.setText("Edit");
//        }
//    }

//    /**
//     * Toggles the edit mode of an attribute by toggling the visibility of the attribute node and its edit node.
//     *
//     * @param value         the attribute node
//     * @param valueEditNode the edit node of the attribute
//     */
//    private void toggleEditModeOfAttribute(Node value, Node valueEditNode) {
//        toggleVisibilityOfNode(value);
//        toggleVisibilityOfNode(valueEditNode);
//    }

//    /**
//     * Toggles the visibility of a given node.
//     *
//     * @param node the node to toggle visibility
//     */
//    private void toggleVisibilityOfNode(Node node) {
//        node.setVisible(!node.isVisible());
//    }
}
