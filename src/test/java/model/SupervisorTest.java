package model;

import de.badwalden.schule.model.Supervisor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Supervisor class.
 */
public class SupervisorTest {

    /**
     * Tests the constructor of the Supervisor class.
     * Checks if the constructor initializes the Supervisor object with the correct ID and name.
     */
    @Test
    public void testConstructor() {
        // Arrange
        int id = 1;
        String name = "Alice Bauer";
        // Act
        Supervisor supervisor = new Supervisor(id, name);
        // Assert
        assertEquals(id, supervisor.getId());
        assertEquals(name, supervisor.getFirstName());
    }
}

