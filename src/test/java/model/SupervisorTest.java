package model;

import de.badwalden.schule.model.Service;
import de.badwalden.schule.model.Supervisor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SupervisorTest {

    @Test
    public void testConstructor() {
        //arrange
        int id = 1;
        String name = "Alice Bauer";
        //act
        Supervisor supervisor = new Supervisor(id, name);
        //assert
        assertEquals(id, supervisor.getId());
        assertEquals(name, supervisor.getFirstName());
    }
}
