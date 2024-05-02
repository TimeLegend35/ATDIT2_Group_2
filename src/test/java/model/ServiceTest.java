package model;

import de.badwalden.schule.model.Service;
import de.badwalden.schule.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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

    @Test
    public void testObjectEquality() {
        // Assemble
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        // Act
        service.setStartTime(startTime);
        service.setEndTime(endTime);
        // Assert
        assertEquals(startTime, service.getStartTime());
        assertEquals(endTime, service.getEndTime());
    }

    @Test
    public void testSetAndGetName() {
        // Assemble
        String name = "Hort";
        // Act
        service.setName(name);
        // Assert
        assertEquals(name, service.getName());
    }

    @Test
    public void testSetAndGetDescription() {
        // Assemble
        String description = "Hort ab Klassenstufe 2.";
        // Act
        service.setDescription(description);
        // Assert
        assertEquals(description, service.getDescription());
    }

    @Test
    public void testSetAndGetYoungestGrade() {
        // Assemble
        int youngestGrade = 2;
        // Act
        service.setYoungestGrade(youngestGrade);
        // Assert
        assertEquals(youngestGrade, service.getYoungestGrade());
    }

    @Test
    public void testSetAndGetOldestGrade() {
        // Assemble
        int oldestGrade = 3;
        // Act
        service.setOldestGrade(oldestGrade);
        // Assert
        assertEquals(oldestGrade, service.getOldestGrade());
    }


    //   @Test
 //   public void testListOperations() {
        // Assemble
//      User supervisor1 = mock(User.class);
//      User supervisor2 = mock(User.class);
//      coSupervisors.add(supervisor1);
//      service.setCoSupervisors(coSupervisors);
        // Assert
//      assertEquals(2, service.getCoSupervisors().size());
//      assertTrue(service.getCoSupervisors().contains(supervisor1));
//      assertTrue(service.getCoSupervisors().contains(supervisor2));
//   }

    @Test
    public void testNullChecks() {
        // Act & Assert
        assertDoesNotThrow(() -> service.setName(null));
        assertDoesNotThrow(() -> service.setDescription(null));
        assertDoesNotThrow(() -> service.setMainSupervisor(null));
        assertDoesNotThrow(() -> service.setCoSupervisors(null));
        assertDoesNotThrow(() -> service.setAttendanceList(null));
    }

}
