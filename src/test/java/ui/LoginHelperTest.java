package ui;

import de.badwalden.schule.model.outOfScope.Admin;
import de.badwalden.schule.ui.helper.LoginHelper;
import de.badwalden.schule.model.helper.Session;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the LoginHelper class.
 */
public class LoginHelperTest {

    /**
     * Initializes JavaFX platform before running any test method.
     */
    @BeforeAll
    public static void setUp() {
        if (!Platform.isFxApplicationThread()) {
            Platform.startup(() -> {});
        }
    }

    /**
     * Tests the authenticate method of the LoginHelper class for invalid credentials.
     * Checks if authentication fails and the current user remains null.
     */
    @Test
    public void testAuthenticateInvalidCredentials (){
        Platform.runLater(() -> {
            // Assemble
            String username = "invalid";
            String password = "invalid";
            // Act
            boolean result = LoginHelper.authenticate(username, password);
            // Assert
            assertFalse(result, "Authentication should fail for invalid credentials");
            assertNull(Session.getInstance().getCurrentUser(), "Current user should be null");
        });
    }
}

