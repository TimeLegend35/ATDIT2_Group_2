package ui;

import de.badwalden.schule.ui.controller.LoginController;
import de.badwalden.schule.ui.helper.Language;
import de.badwalden.schule.ui.helper.LanguageHelper;
import de.badwalden.schule.ui.views.LoginView;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the LoginView class.
 */
public class LoginViewTest {

    /**
     * Initializes JavaFX platform before running any test method.
     */
    @BeforeAll
    public static void setUp() {
        Setup.start_up_javaFX_plattform();

    }

    /**
     * Tests the updateTextsFromResourceBundle() method of the LoginView class.
     * Checks if UI elements' texts are updated correctly based on the resource bundle for French language.
     */
    @Test
    public void testUpdateFrenchTextsFromResourceBundle() {
        Platform.runLater(() -> {
            // Assemble
            LoginView loginView = new LoginView();
            Text userNameLabel = loginView.userNameLabel;
            TextField userNameTextField = loginView.getUserNameTextField();
            Text passwordLabel = loginView.passwordLabel;
            PasswordField passwordField = loginView.getPasswordTextField();
            CheckBox stayLoggedInCheckBox = loginView.getStayLoggedInCheckBox();
            Button signInButton = loginView.getSignInButton();
            // Act
            LanguageHelper.setLocale(Language.FRENCH);
            loginView.updateTextsFromResourceBundle();
            // Assert
            assertEquals("Nom d'utilisateur", userNameLabel.getText(), "Username label should be updated");
            assertEquals("Mot de passe", passwordLabel.getText(), "Password label should be updated");
            assertEquals("Rester connecté", stayLoggedInCheckBox.getText(), "Stay logged in checkbox label should be updated");
            assertEquals("Se connecter", signInButton.getText(), "Sign in button text should be updated");
        });
    }

    @Test
    public void testUpdateEnglishTextsFromResourceBundle() {
        Platform.runLater(() -> {
            // Assemble
            LoginView loginView = new LoginView();
            Text userNameLabel = loginView.userNameLabel;
            TextField userNameTextField = loginView.getUserNameTextField();
            Text passwordLabel = loginView.passwordLabel;
            PasswordField passwordField = loginView.getPasswordTextField();
            CheckBox stayLoggedInCheckBox = loginView.getStayLoggedInCheckBox();
            Button signInButton = loginView.getSignInButton();
            // Act
            LanguageHelper.setLocale(Language.ENGLISH);
            loginView.updateTextsFromResourceBundle();
            // Assert
            assertEquals("Username", userNameLabel.getText(), "Username label should be updated");
            assertEquals("Password", passwordLabel.getText(), "Password label should be updated");
            assertEquals("Stay Logged In", stayLoggedInCheckBox.getText(), "Stay logged in checkbox label should be updated");
            assertEquals("Sign In", signInButton.getText(), "Sign in button text should be updated");
        });
    }

    /**
     * Tests the updateTextWithLoginController() method of the LoginView class.
     * Checks if UI elements' texts are updated correctly based on the language change handled by the LoginController.
     */
    @Test
    public void testUpdateTextWithLoginController(){
        Platform.runLater(() -> {
            // Assemble
            LoginView loginView = new LoginView();
            LoginController loginController = new LoginController(loginView);
            // Act
            loginController.handleLanguageChange(Language.FRENCH);
            // Assert
            assertEquals("Nom d'utilisateur:", loginController.getLoginView().userNameLabel.getText(), "Username label should be updated");
            assertEquals("Mot de passe:", loginController.getLoginView().passwordLabel.getText(), "Password label should be updated");
            assertEquals("Rester connecté", loginController.getLoginView().getStayLoggedInCheckBox().getText(), "Stay logged in checkbox label should be updated");
            assertEquals("Se connecter", loginController.getLoginView().getSignInButton().getText(), "Sign in button text should be updated");
        });
    }
}

