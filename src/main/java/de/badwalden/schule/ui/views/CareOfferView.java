package de.badwalden.schule.ui.views;

import de.badwalden.schule.Main;
import de.badwalden.schule.model.*;
import de.badwalden.schule.ui.controller.CareOfferController;
import de.badwalden.schule.ui.controller.CareOfferMarketplaceController;
import de.badwalden.schule.ui.helper.DialogHelper;
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

    public CareOfferView(CareOffer offer) {
        super(15); // Adds spacing between child elements of the VBox
        controller = new CareOfferController(this);
        this.careOffer = offer;

        // check what user type is logged in and plot according
        User user = Session.getInstance().getCurrentUser();

        if (user instanceof Admin || user instanceof Parent) {

            // Set padding around the entire VBox container
            setPadding(new Insets(15));

            // Create a button to go back
            Button backButton = new Button("Zurück");
            backButton.setId("back"); // Set the button's ID to the offer's ID
            backButton.setOnAction(event -> navigationHelper.setContentView("Betreuungsmarktplatz"));

            HBox topRightContainer = new HBox(backButton);
            topRightContainer.setAlignment(Pos.TOP_LEFT);
            topRightContainer.setPadding(new Insets(10));

            // Only the Admin and the Main Supervisor should be able to edit Care Offers
            if(user.getId() == careOffer.getMainSupervisor().getId() || user instanceof Admin) {
                // Create a button to edit details
                Button editButton = new Button("Edit");
                editButton.setId(String.valueOf(offer.getId())); // Set the button's ID to the offer's ID
                editButton.setOnAction(event -> this.changeEditView(editButton));
                topRightContainer.getChildren().add(editButton);
            }

            // Create labels for the offer's title and description
            Label titleLabel = new Label("Titel: ");
            titleLabel.setFont(new Font(FONT_SIZE)); // Set font size for title
            titleLabelValue = new Label();
            titleLabelValue.setFont(new Font(FONT_SIZE)); // Set font size for title
            titleTextField = new TextField();
            titleTextField.setVisible(false);
            uiElements.add(new ObjectPageAttributeElementsContainer(titleLabel, titleLabelValue, titleTextField));

            Label descriptionLabel = new Label("Beschreibung: ");
            descriptionLabel.setFont(new Font(FONT_SIZE)); // Set font size for title
            descriptionLabelValue = new Label();
            descriptionLabelValue.setFont(new Font(FONT_SIZE)); // Set font size for description
            descriptionLabelValue.setWrapText(true); // Allows the description to wrap within the label width
            descriptionTextField = new TextField();
            descriptionTextField.setVisible(false);
            uiElements.add(new ObjectPageAttributeElementsContainer(descriptionLabel, descriptionLabelValue, descriptionTextField));

            Label numberOfSeatsLabel = new Label("Verfügbare Plätze: ");
            numberOfSeatsLabel.setFont(new Font(FONT_SIZE)); // Set font size for title
            numberOfSeatsLabelValue = new Label();
            numberOfSeatsLabelValue.setFont(new Font(FONT_SIZE)); // Set font size for description
            numberOfSeatsLabelValue.setWrapText(true); // Allows the description to wrap within the label width
            numberOfSeatsTextField = new TextField();
            numberOfSeatsTextField.setVisible(false);
            uiElements.add(new ObjectPageAttributeElementsContainer(numberOfSeatsLabel, numberOfSeatsLabelValue, numberOfSeatsTextField));

            Label youngestGradeLabel = new Label("Jüngste Stufe: ");
            youngestGradeLabel.setFont(new Font(FONT_SIZE)); // Set font size for title
            youngestGradeLabelValue = new Label();
            youngestGradeLabelValue.setFont(new Font(FONT_SIZE)); // Set font size for description
            youngestGradeLabelValue.setWrapText(true); // Allows the description to wrap within the label width
            youngestGradeTextField = new TextField();
            youngestGradeTextField.setVisible(false);
            uiElements.add(new ObjectPageAttributeElementsContainer(youngestGradeLabel, youngestGradeLabelValue, youngestGradeTextField));

            Label oldestGradeLabel = new Label("Jüngste Stufe: ");
            oldestGradeLabel.setFont(new Font(FONT_SIZE)); // Set font size for title
            oldestGradeLabelValue = new Label();
            oldestGradeLabelValue.setFont(new Font(FONT_SIZE)); // Set font size for description
            oldestGradeLabelValue.setWrapText(true); // Allows the description to wrap within the label width
            oldestGradeTextField = new TextField();
            oldestGradeTextField.setVisible(false);
            uiElements.add(new ObjectPageAttributeElementsContainer(oldestGradeLabel, oldestGradeLabelValue, oldestGradeTextField));

            controller.updateValuesFromObject(careOffer);

            GridPane gridPane = new GridPane();
            gridPane.setHgap(10);
            gridPane.setVgap(10);

            addAllAttributesToGridPane(gridPane);

            // Create a button to register for this care offer
            Button registerButton = new Button("Kind Anmelden");
            registerButton.setId(String.valueOf(offer.getId())); // Set the button's ID to the offer's ID
            registerButton.setOnAction(event -> {
                // Create a dialog
                Dialog<User> dialog = new Dialog<>();
                dialog.setTitle("Register Child");

                // Set the button types
                ButtonType registerButtonType = new ButtonType("Register", ButtonBar.ButtonData.OK_DONE);
                dialog.getDialogPane().getButtonTypes().addAll(registerButtonType, ButtonType.CANCEL);

                if(user instanceof Parent) {
                    // If the user has multiple children, create a dropdown
                    if (((Parent) user).getChildren().size() > 1){
                        // Create a dropdown for children
                        ComboBox<User> childrenDropdown = new ComboBox<>();
                        // Assuming you have a method to get the list of children of a user object
                        if(Session.getInstance().getCurrentUser() instanceof Parent) {
                            List<Student> childrenList = ((Parent) Session.getInstance().getCurrentUser()).getChildren();
                            childrenDropdown.getItems().addAll(childrenList);
                        }


                        // Set the dialog content
                        GridPane grid = new GridPane();
                        grid.add(new Label("Select Child:"), 0, 0);
                        grid.add(childrenDropdown, 0, 1);
                        dialog.getDialogPane().setContent(grid);

                        // Request focus on the child dropdown by default
                        Platform.runLater(childrenDropdown::requestFocus);

                        // Convert the result to a user object when the register button is clicked
                        dialog.setResultConverter(dialogButton -> {
                            if (dialogButton == registerButtonType) {
                                return childrenDropdown.getValue();
                            }
                            return null;
                        });
                    // If the User has only one child
                    } else if (((Parent) user).getChildren().size() == 1){
                        Student child = ((Parent) Session.getInstance().getCurrentUser()).getChildren().get(0);
                        GridPane grid = new GridPane();
                        grid.add(new Label("Do you want to register " + String.valueOf(child) + "?"), 0, 0);
                        dialog.getDialogPane().setContent(grid);
                        dialog.setResultConverter(dialogButton -> {
                            if (dialogButton == registerButtonType) {
                                return child;
                            }
                            return null;
                        });

                    } else {
                        DialogHelper.showAlertDialog(Alert.AlertType.ERROR, "Register Child", "No Children are connected to the current user. Please contact an Administrator.");
                    }
                }


                // Show the dialog and handle the result
                Optional<User> result = dialog.showAndWait();
                result.ifPresent(selectedChild -> {
                    // Do something with the selected child
                    System.out.println("Selected child: " + selectedChild.getFirstName());
                    careOffer.addStudentToStudentList((Student) selectedChild);
                });
            });


            // Create a container for each offer's details and add them to the VBox
            VBox offerBox = new VBox(10); // Adds spacing between elements in each offer container
            offerBox.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID,
                    CornerRadii.EMPTY, new BorderWidths(2))));
            offerBox.setPadding(new Insets(10)); // Add padding inside the border
            offerBox.getChildren().addAll(gridPane, registerButton);

            this.getChildren().addAll(topRightContainer, offerBox);
        }

    }

    /**
     * Internal class for storing elements related to an object pages attributes.
     */
    public class ObjectPageAttributeElementsContainer {
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
     * @param  gridPane   the GridPane to which attributes will be added
     */
    private void addAllAttributesToGridPane(GridPane gridPane) {
        for (ObjectPageAttributeElementsContainer container : uiElements) {
            addAttributeToGridPane(gridPane, container.titleLabel, container.initialUiNode, container.editModeUiNode);
        }
    }

    /**
     * Adds a Single attribute to a GridPane.
     *
     * @param  gridPane         the GridPane to which the attribute will be added
     * @param  label            the label for the attribute
     * @param  initialUiNode    the initial UI node for the attribute
     * @param  editModeUiNode   the UI node for the attribute in edit mode
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
     * @param  editButton  the Button used to toggle edit mode
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
     * @param  value         the attribute node
     * @param  valueEditNode the edit node of the attribute
     */
    private void toggleEditModeOfAttribute(Node value, Node valueEditNode) {
        toggleVisibilityOfNode(value);
        toggleVisibilityOfNode(valueEditNode);
    }

    /**
     * Toggles the visibility of a given node.
     *
     * @param  node   the node to toggle visibility
     */
    private void toggleVisibilityOfNode(Node node) {
        node.setVisible(!node.isVisible());
    }
}
