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

public class LoginViewTest {

    @BeforeAll
    public static void setUp(){
        Platform.startup(() -> {
        });
    }

    @Test
    void testUpdateTextsFromResourceBundle() {
        Platform.runLater(() -> {
            //assemble
            LoginView loginView = new LoginView();
            Text userNameLabel = loginView.userNameLabel;
            TextField userNameTextField = loginView.getUserNameTextField();
            Text passwordLabel = loginView.passwordLabel;
            PasswordField passwordField = loginView.getPasswordField();
            CheckBox stayLoggedInCheckBox = loginView.getStayLoggedInCheckBox();
            Button signInButton = loginView.getSignInButton();
            //act
            LanguageHelper.setLocale("Français");
            loginView.updateTextsFromResourceBundle();
            //assert
            assertEquals("Nom d'utilisateur:", userNameLabel.getText(), "Username label should be updated");
            assertEquals("Mot de passe:", passwordLabel.getText(), "Password label should be updated");
            assertEquals("Rester connecté", stayLoggedInCheckBox.getText(), "Stay logged in checkbox label should be updated");
            assertEquals("Se connecter", signInButton.getText(), "Sign in button text should be updated");
        });
    }

}
