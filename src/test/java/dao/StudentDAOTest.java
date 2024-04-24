package dao;

import de.badwalden.schule.dao.StudentDAO;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.AssertJUnit.*;

public class StudentDAOTest {

    @Test
    public void testGetStudentsIdFromParent() {
        // Test the getStudentsIdFromParent method with a valid parent ID
        List<Object[]> results = StudentDAO.getStudentsIdFromParent(1);
        assertNotNull(results);
        assertTrue(results.size() > 0); // Assuming there are students assigned to the parent
    }

    @Test
    public void testGet() {
        // Test the get method with a valid student ID
        List<Object[]> results = StudentDAO.get(1);
        assertNotNull(results);
        assertEquals(1, results.size());
    }

    @Test
    public void testWrite() {
        // Test the write method
        assertFalse(StudentDAO.write()); // Assuming write method always returns false
    }
}
