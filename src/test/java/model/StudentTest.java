package model;

import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.model.Service;
import de.badwalden.schule.model.Student;
import de.badwalden.schule.model.Supervisor;
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

    @Test
    public void testSetAndGetAge() {
        // Assemble
        Student bob = new Student();
        int age = 10;
        // Act
        bob.setAge(age);
        // Assert
        assertEquals(age, bob.getAge());
    }

    @Test
    public void testBoundaryConditionsForAge() {
        // Assemble
        int minAge = 0;
        int maxAge = 120;
        // Act
        // Assert
        assertDoesNotThrow(() -> student.setAge(minAge));
        assertDoesNotThrow(() -> student.setAge(maxAge));
    }

    @Test
    public void testRegisterAndDeregisterStudentFromService() {
        // Assemble
        Supervisor supervisor = new Supervisor(1, "Zoe");
        CareOffer careOffer = new CareOffer(1, supervisor, 3, 1, "Hort", "Beschreibung Hort", 5);
        int initialSeatsAvailable = careOffer.getSeatsAvailable();
        // Act & Assert
        student.registerStudentFromService(careOffer);
        assertEquals(initialSeatsAvailable - 1, careOffer.getSeatsAvailable(), "Seats available should decrease after registration");
        assertTrue(student.isRegisteredForOffer(careOffer), "Student should be registered for the service");
        // Act & Assert
        student.deregisterStudentFromService(careOffer);
        assertEquals(initialSeatsAvailable, careOffer.getSeatsAvailable(), "Seats available should increase after deregistration");
        assertFalse(student.isRegisteredForOffer(careOffer), "Student should not be registered for the service after deregistration");
    }

    @Test
    public void testIsRightOfService() {
        // Assemble
        Supervisor supervisor = new Supervisor(1, "Zoe");
        CareOffer careOffer = new CareOffer(1, supervisor, 3, 1, "Hort", "Beschreibung Hort", 1);
        // Act & Assert
        student.setClassYear(2);
        student.setRightOfService(true);
        assertTrue(student.isRightOfService(careOffer), "Student should have right of service");
        // Act & Assert
        student.setClassYear(4);
        assertFalse(student.isRightOfService(careOffer), "Student should not have right of service when outside grade range");
        // Act & Assert
        careOffer.setSeatsAvailable(0);
        assertFalse(student.isRightOfService(careOffer), "Student should not have right of service when no available seats");
    }

    // @Test
    //public void testUpdateMethod() {
    //  student.setId(1);
        //student.setFirstName("John");
        //student.setLastName("Doe");
       // student.setAge(16);
        //student.setCompulsorySchooling(true);
        //student.setRightOfService(true);
        //CareOffer careOffer = new CareOffer();
       // List<Service> serviceList = new ArrayList<>();
       // serviceList.add(new Service());
       // student.setServiceList(serviceList);
      //  StudentDAO studentDAO = Mockito.mock(StudentDAO.class);
        //CareOfferDAO careOfferDAO = Mockito.mock(CareOfferDAO.class);
       // Mockito.when(studentDAO.write(Mockito.anyList())).thenReturn(1);
       // student.update();
        //Mockito.verify(studentDAO, Mockito.times(1)).write(Mockito.anyList());
    //}

    @Test
    public void testToObjectArrayMethod() {
        // Assemble
        Student alice = new Student();
        alice.setId(1);
        alice.setClassYear(3);
        alice.setFirstName("Alice");
        alice.setLastName("Mueller");
        alice.setAge(9);
        alice.setCompulsorySchooling(true);
        alice.setRightOfService(true);
        // Act
        Object[] objectArray = student.toObjectArray();
        // Assert
        assertEquals(1, objectArray[0]);
        assertEquals(3, objectArray[1]);
        assertEquals("Alice", objectArray[2]);
        assertEquals("Mueller", objectArray[3]);
        assertEquals(9, objectArray[4]);
        assertTrue((boolean) objectArray[5]);
        assertTrue((boolean) objectArray[6]);
    }

    @Test
    public void testSetAndGetClassYear() {
        // Assemble
        int classYear = 4;
        // Act
        student.setClassYear(classYear);
        // Assert
        assertEquals(classYear, student.getClassYear(), "Class year should be set correctly");
    }
}

