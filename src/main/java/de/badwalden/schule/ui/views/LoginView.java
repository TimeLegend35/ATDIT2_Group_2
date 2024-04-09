package de.badwalden.schule.ui.views;

import de.badwalden.schule.ui.controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class LoginView extends BorderPane {
    private final LoginController loginController = new LoginController(this);
    private Text userNameLabel;
    private TextField userNameTextField;
    private Text passwordLabel;
    private PasswordField passwordField;
    private CheckBox stayLoggedInCheckBox;
    private Button signInButton;
    private Text scenetitle;

    public LoginView() {

        VBox centerContent = new VBox();
        centerContent.setAlignment(Pos.CENTER);
        centerContent.setSpacing(10);

        scenetitle = new Text("Learning Hub\nBad Walden");
        scenetitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        userNameLabel = new Text("Benutzername:");
        userNameTextField = new TextField();
        userNameTextField.setPromptText("Benutzername");
        userNameTextField.setMaxWidth(200);

        passwordLabel = new Text("Passwort:");
        passwordField = new PasswordField();
        passwordField.setPromptText("Passwort");
        passwordField.setMaxWidth(200);

        stayLoggedInCheckBox = new CheckBox("Angemeldet bleiben");

        signInButton = new Button("Anmelden");
        signInButton.setDefaultButton(true);
        signInButton.setOnAction(event -> loginController.handleLoginButtonPressed());

        centerContent.getChildren().addAll(scenetitle, userNameLabel, userNameTextField, passwordLabel, passwordField, stayLoggedInCheckBox, signInButton);

        VBox centerContainer = new VBox(centerContent);
        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.setPadding(new Insets(25));
        centerContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        setCenter(centerContainer);
    }

    public TextField getUserNameTextField() {
        return userNameTextField;
    }

    public PasswordField getPasswordField() {
        return passwordField;
    }

    public CheckBox getStayLoggedInCheckBox() {
        return stayLoggedInCheckBox;
    }

    public Button getSignInButton() {
        return signInButton;
    }
}
