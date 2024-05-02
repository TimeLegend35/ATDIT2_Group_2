package model;

import de.badwalden.schule.model.Parent;
import de.badwalden.schule.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Unit tests for the Parent class.
 */
public class ParentTest {

    private Parent parent;
    private Student alice;
    private Student bob;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    public void setUp(){
        parent = new Parent(500, "String firstName", "String lastName", "String cityOfResidence");
        alice = new Student();
        bob = new Student();
    }

    /**
     * Tests the getChildren() method.
     * Checks if initially no children are present.
     */
    @Test
    public void testGetChildren(){
        // Assemble
        List<Student> children = new ArrayList<>();
        // Act
        parent.setChildren(children);
        // Assert
        assertEquals(0, parent.getChildren().size(), "Initially, no children should be present");
    }

    /**
     * Tests the setChildren() method.
     * Checks if the number of children and their presence match after setting.
     */
    @Test
    public void testSetChildren(){
        // Assemble
        List<Student> children = new ArrayList<>();
        // Act
        children.add(alice);
        children.add(bob);
        parent.setChildren(children);
        // Assert
        assertEquals(2, parent.getChildren().size(), "Number of children should match after setting");
        assertTrue(parent.getChildren().contains(alice), "A should be present");
        assertTrue(parent.getChildren().contains(bob), "B should be present");
    }

    /**
     * Tests the getId() method.
     * Checks if the ID matches after setting.
     */
    @Test
    public void testGetId(){
        // Assemble
        // Act
        int id = 123456;
        parent.setId(id);
        // Assert
        assertEquals(id, parent.getId(), "IDs should match");
    }

    /**
     * Tests the set and getName() methods.
     * Checks if first and last names match after setting.
     */
    @Test
    public void testSetAndGetName(){
        // Assemble
        // Act
        String firstName = "Bob";
        parent.setFirstName(firstName);
        String lastName = "Bauer";
        parent.setLastName(lastName);
        // Assert
        assertEquals(firstName, parent.getFirstName(), "FirstNames should match");
        assertEquals(lastName, parent.getLastName(), "LastNames should match");
    }

    @Test
    public void testSetChildrenListEmpty() {
        // Assemble
        Parent parent = new Parent(500, "FirstName", "LastName", "String cityOfResidence");
        List<Student> emptyChildren = new ArrayList<>();
        // Act
        parent.setChildren(emptyChildren);
        // Assert
        assertTrue(parent.getChildren().isEmpty(), "Setting an empty list should result in an empty children list");
    }

    @Test
    public void testMinValueForID() {
        // Assemble
        int minValue = Integer.MIN_VALUE;
        Parent parent = new Parent(minValue, "FirstName", "LastName", "City");
        // Act
        // Assert
        assertEquals(minValue, parent.getId(), "Setting the minimum possible ID value should work");
    }

    @Test
    public void testMaxValueForID() {
        // Assemble
        int maxValue = Integer.MAX_VALUE;
        // Act
        parent.setId(maxValue);
        // Assert
        assertEquals(maxValue, parent.getId(), "Setting the maximum possible ID value should work");
    }
}
