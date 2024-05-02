package model;

import de.badwalden.schule.model.Service;
import de.badwalden.schule.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Unit tests for the Service class.
 */
public class ServiceTest {

    private Student bob;
    private Service service;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    public void setUp(){
        bob = new Student();
        service = new Service();
    }

    @Test
    public void testSetAndGetId() {
        // Assemble
        int id = 123;
        // Act
        service.setId(id);
        // Assert
        assertEquals(id, service.getId());
    }

    @Test
    public void testSetAndGetStartTime() {
        // Assemble
        Calendar startTime = Calendar.getInstance();
        // Act
        service.setStartTime(startTime);
        // Assert
        assertEquals(startTime, service.getStartTime());
    }

    @Test
    public void testSetAndGetEndTime() {
        // Assemble
        Calendar endTime = Calendar.getInstance();
        // Act
        service.setEndTime(endTime);
        // Assert
        assertEquals(endTime, service.getEndTime());
    }

    @Test
    public void testSetAndGetSeatsAvailable() {
        // Assemble
        int seatsAvailable = 10;
        // Act
        service.setSeatsAvailable(seatsAvailable);
        // Assert
        assertEquals(seatsAvailable, service.getSeatsAvailable());
    }
}
