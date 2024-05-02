package dao;

import de.badwalden.schule.dao.DBConnector;
import de.badwalden.schule.dao.DatabaseInteractions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;


public class DBInteractionsTest {

    @Test
    public void testDBConnectionInstance() {
        // Test that the DBConnector instance is not null
        assertNotNull(DatabaseInteractions.dbConnection);
    }

    @Test
    public void testDBConnectionSingleton() {
        // Test that the DBConnector instance is a singleton
        DBConnector instance1 = DatabaseInteractions.dbConnection;
        DBConnector instance2 = DatabaseInteractions.dbConnection;
        assertSame(instance1, instance2);
    }
}
