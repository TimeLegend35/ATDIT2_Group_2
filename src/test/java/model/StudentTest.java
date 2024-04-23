package model;

import de.badwalden.schule.model.Service;
import de.badwalden.schule.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Student class.
 */
public class StudentTest {

    private Student student;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    public void setUp(){
        student = new Student();
    }

    /**
     * Tests the compulsory schooling status of the student.
     * Checks if compulsory schooling status is correctly set and changed.
     */
    @Test
    public void testCompulsorySchooling(){
        // Assemble
        boolean initialCompulsorySchooling = student.isCompulsorySchooling();
        // Act
        student.setCompulsorySchooling(true);
        // Assert
        assertTrue(student.isCompulsorySchooling(), "Compulsory schooling should be true after setting");
        assertNotEquals(initialCompulsorySchooling, student.isCompulsorySchooling(), "Compulsory schooling should have changed");
    }

    /**
     * Tests the service list of the student.
     * Checks if the service list matches after setting.
     */
    @Test
    public void testServiceList(){
        // Assemble
        List<Service> serviceList = new ArrayList<>();
        Service fussball = new Service();
        Service basteln = new Service();
        // Act
        serviceList.add(fussball);
        serviceList.add(basteln);
        student.setServiceList(serviceList);
        // Assert
        assertEquals(serviceList, student.getServiceList(), "Service list should match after setting");
    }
}

