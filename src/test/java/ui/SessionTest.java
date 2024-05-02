package ui;

import de.badwalden.schule.model.Parent;
import de.badwalden.schule.model.User;
import de.badwalden.schule.model.helper.Session;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit tests for the Session class.
 */
public class SessionTest {

    /**
     * Tests the setCurrentUser() and getCurrentUser() methods of the Session class.
     * Checks if the current user is correctly set and retrieved.
     */
    @Test
    void testSetAndGetUser() {
        // Assemble
        Session session = Session.getInstance();
        User user = new Parent();
        // Act
        session.setCurrentUser(user);
        User currentUser = session.getCurrentUser();
        // Assert
        assertNotNull(currentUser, "Current user should not be null after setting");
        assertEquals(user, currentUser, "Current user should match the one set");
    }

}

