package dao;

import de.badwalden.schule.dao.ParentDAO;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.*;

public class ParentDAOTest {

    @Test
    public void testGet() {
        // Test the get method with a valid parent ID
        List<Object[]> results = ParentDAO.get(1);
        assertNotNull(results);
        assertEquals(1, results.size());
    }

    @Test
    public void testWrite() {
        // Test the write method
        assertFalse(ParentDAO.write()); // Assuming write method always returns false
    }
}
