package de.badwalden.schule.ui.views;

import de.badwalden.schule.dao.DBConnector;
import de.badwalden.schule.ui.controller.LoginController;
import de.badwalden.schule.ui.helper.DialogHelper;
import de.badwalden.schule.ui.helper.Language;
import de.badwalden.schule.ui.helper.LanguageHelper;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginView extends BorderPane {
    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());
    private final LoginController loginController = new LoginController(this);
    private ComboBox<String> languageComboBox;
    public Text userNameLabel;
    private TextField userNameTextField;
    public Text passwordLabel;
    private PasswordField passwordTextField;
    private CheckBox stayLoggedInCheckBox;
    private Button signInButton;
    private Text scenetitle;

    public LoginView() {

        addLanguageBox();

        HBox topRightContainer = new HBox(languageComboBox);
        topRightContainer.setAlignment(Pos.TOP_RIGHT);
        topRightContainer.setPadding(new Insets(10));
        setTop(topRightContainer);

        VBox centerContent = new VBox();
        centerContent.setAlignment(Pos.CENTER);
        centerContent.setSpacing(10);

        scenetitle = new Text(LanguageHelper.getString("sidebar_title") + "\nBad Walden");
        scenetitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        instantiateAttributes();

        centerContent.getChildren().addAll(scenetitle, userNameLabel, userNameTextField, passwordLabel, passwordTextField, stayLoggedInCheckBox, signInButton);

        VBox centerContainer = new VBox(centerContent);
        centerContainer.setAlignment(Pos.CENTER);
        centerContainer.setPadding(new Insets(25));
        centerContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        setCenter(centerContainer);
    }

    public void instantiateAttributes() {
        userNameLabel = new Text(LanguageHelper.getString("login_username") + ":");
        userNameTextField = new TextField();
        userNameTextField.setPromptText(LanguageHelper.getString("login_username"));
        userNameTextField.setMaxWidth(200);

        passwordLabel = new Text(LanguageHelper.getString("login_password") + ":");
        passwordTextField = new PasswordField();
        passwordTextField.setPromptText(LanguageHelper.getString("login_password"));
        passwordTextField.setMaxWidth(200);

        stayLoggedInCheckBox = new CheckBox(LanguageHelper.getString("login_stayLoggedIn"));

        signInButton = new Button(LanguageHelper.getString("login_signIn"));
        signInButton.setDefaultButton(true);
        signInButton.setOnAction(event -> loginController.handleLoginButtonPressed(userNameTextField.getText(), passwordTextField.getText()));
    }

    public ComboBox addLanguageBox() {
        languageComboBox = new ComboBox<>();
        languageComboBox.setItems(FXCollections.observableArrayList(
                Language.GERMAN.getId(),
                Language.ENGLISH.getId(),
                Language.FRENCH.getId()
        ));

        // Set a StringConverter to handle conversion between ID and display name
        languageComboBox.setConverter(new StringConverter<String>() {
            @Override
            public String toString(String id) {
                // Display the language name corresponding to the ID
                for (Language language : Language.values()) {
                    if (language.getId().equals(id)) {
                        return language.toString();
                    }
                }
                // Return null if no match found
                return null;
            }

            @Override
            public String fromString(String string) {
                // Return the ID directly when converting from display name to ID
                return string;
            }
        });

        // Select the first language by default (German)
        languageComboBox.getSelectionModel().selectFirst();
        LanguageHelper.setLocale(Language.GERMAN);

        // Add a listener to update the language when the selected language changes
        languageComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                try {
                    // Pass the selected language to the helper
                    loginController.handleLanguageChange(Language.getLanguage(newVal));
                } catch (NoSuchElementException e) {
                    logger.log(Level.SEVERE, "Error handling the language change", e);
                    DialogHelper.showAlertDialog(Alert.AlertType.ERROR, "Language Change Error", "Error handling the language change. If the Error persists, please contact the Administrator.");
                }
            }
        });

        return languageComboBox;
    }

    public void updateTextsFromResourceBundle() {
        userNameLabel.setText(LanguageHelper.getString("login_username"));
        userNameTextField.setPromptText(LanguageHelper.getString("login_username"));
        passwordLabel.setText(LanguageHelper.getString("login_password"));
        passwordTextField.setPromptText(LanguageHelper.getString("login_password"));
        stayLoggedInCheckBox.setText(LanguageHelper.getString("login_stayLoggedIn"));
        signInButton.setText(LanguageHelper.getString("login_signIn"));
    }
}
