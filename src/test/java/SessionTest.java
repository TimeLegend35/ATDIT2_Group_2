import de.badwalden.schule.model.Parent;
import de.badwalden.schule.model.User;
import de.badwalden.schule.ui.helper.Session;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SessionTest {

    @Test
    void testSetAndGetUser() {
        //assemble
        Session session = Session.getInstance();
        User user = new Parent();
        //act
        session.setCurrentUser(user);
        User currentUser = session.getCurrentUser();
        //assert
        assertNotNull(currentUser, "Current user should not be null after setting");
        assertEquals(user, currentUser, "Current user should match the one set");
    }

}
