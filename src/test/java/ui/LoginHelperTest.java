package ui;

import de.badwalden.schule.model.helper.ModelBuilder;
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
        Setup.start_up_javaFX_plattform();

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
    @Test
    public void testStudentLogiun(){
        Platform.runLater(() -> {
            // Assemble
            String username = "";
            String password = "student";
            // Act
            boolean result = LoginHelper.authenticate(username, password);
            // Assert
            assertTrue(result);
            assertEquals(ModelBuilder.buildModelFromStudent(1),Session.getInstance().getCurrentUser(), "Current user should be null");
        });
    }
    @Test
    public void testParentLogiun(){
        Platform.runLater(() -> {
            // Assemble
            String username = "";
            String password = "parent";
            // Act
            boolean result = LoginHelper.authenticate(username, password);
            // Assert
            assertTrue(result);
            assertEquals(ModelBuilder.buildModelFromParent(1),Session.getInstance().getCurrentUser(), "Current user should be null");
        });
    }
    @Test
    public void testParentOneChildLogiun(){
        Platform.runLater(() -> {
            // Assemble
            String username = "";
            String password = "parentOneChild";
            // Act
            boolean result = LoginHelper.authenticate(username, password);
            // Assert
            assertTrue(result);
            assertEquals(ModelBuilder.buildModelFromParent(3),Session.getInstance().getCurrentUser(), "Current user should be null");
        });
    }
    @Test
    public void testParentYoungChildLogiun(){
        Platform.runLater(() -> {
            // Assemble
            String username = "";
            String password = "parentYoungChild";
            // Act
            boolean result = LoginHelper.authenticate(username, password);
            // Assert
            assertTrue(result);
            assertEquals(ModelBuilder.buildModelFromParent(12),Session.getInstance().getCurrentUser(), "Current user should be null");
        });
    }

}

