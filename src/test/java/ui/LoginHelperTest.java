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
    public static void setUp(){
        Platform.startup(() -> {
        });
    }

    /**
     * Tests the authenticate method of the LoginHelper class for admin credentials.
     * Checks if admin authentication succeeds and sets the current user as an instance of Admin.
     */
    @Test
    public void testAuthenticateAdmin (){
        Platform.runLater(() -> {
            // Assemble
            String username = "admin";
            String password = "admin";
            // Act
            boolean result = LoginHelper.authenticate(username, password);
            // Assert
            assertTrue(result, "Admin authentication should succeed");
            assertTrue(Session.getInstance().getCurrentUser() instanceof Admin, "Current user should be an instance of Admin");
        });
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

