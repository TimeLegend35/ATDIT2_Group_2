package model;

import de.badwalden.schule.model.Service;
import de.badwalden.schule.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the Service class.
 */
public class ServiceTest {

    private Student bob;
    private Service basteln;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    public void setUp(){
        bob = new Student();
        basteln = new Service();
    }

    /**
     * Tests the addStudentToStudentList() method.
     * Checks if a student is successfully added to the student list of the service.
     */
    @Test
    public void testAddStudentToStudentList(){
        // Assemble
        // Act
        basteln.setNumberOfSeats(2);
        basteln.addStudentToStudentList(bob);
        // Assert
        assertTrue(basteln.getStudentList().contains(bob));
    }

    /**
     * Tests the addStudentToStudentList() method when the service is full.
     * Checks if a student is not added to the student list when the service is full.
     */
    @Test
    public void testAddStudentWhenServiceIsFull(){
        // Assemble
        Student clemens = new Student();
        Student dunja = new Student();
        // Act
        basteln.setNumberOfSeats(2);
        basteln.addStudentToStudentList(clemens);
        basteln.addStudentToStudentList(dunja);
        basteln.addStudentToStudentList(bob);
        // Assert
        assertFalse(basteln.getStudentList().contains(bob));
    }
}
