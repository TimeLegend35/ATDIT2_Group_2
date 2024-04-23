package ui;

import de.badwalden.schule.model.Admin;
import de.badwalden.schule.ui.helper.LoginHelper;
import de.badwalden.schule.ui.helper.Session;
import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginHelperTest {

    @BeforeAll
    public static void setUp(){
        Platform.startup(() -> {
        });
    }

    @Test
    public void testAuthenticateAdmin (){
        Platform.runLater(() -> {
            //assemble
            String username = "admin";
            String password = "admin";
            //act
            boolean result = LoginHelper.authenticate(username, password);
            //assert
            assertTrue(result, "Admin authentication should succeed");
            assertTrue(Session.getInstance().getCurrentUser() instanceof Admin, "Current user should be an instance of Admin");
        });
    }

    @Test
    public void testAuthenticateInvalidCredentials (){
        Platform.runLater(() -> {
        //assemble
        String username = "invalid";
        String password = "invalid";
        //act
        boolean result = LoginHelper.authenticate(username, password);
        //assert
        assertFalse(result, "Authentication should fail for invalid credentials");
        assertNull(Session.getInstance().getCurrentUser(), "Current user should be null");
        });
    }

}
