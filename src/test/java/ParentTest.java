import de.badwalden.schule.model.Parent;
import de.badwalden.schule.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParentTest {

    private Parent parent;
    private Student alice;
    private Student bob;

    @BeforeEach
    public void setUp(){
        parent = new Parent();
        alice = new Student();
        bob = new Student();
    }

    @Test
    public void testGetChildren(){
        //assemble
        List<Student> children = new ArrayList<>();
        //act
        parent.setChildren(children);
        //assert
        assertEquals(0, parent.getChildren().size(), "Initially, no children should be present");
    }

    @Test
    public void testSetChildren(){
        //assemble
        List<Student> children = new ArrayList<>();
        //act
        children.add(alice);
        children.add(bob);
        parent.setChildren(children);
        //assert
        assertEquals(2, parent.getChildren().size(), "Number of children should match after setting");
        assertTrue(parent.getChildren().contains(alice), "A should be present");
        assertTrue(parent.getChildren().contains(bob), "B should be present");
    }

    @Test
    public void testGetId(){
        //assemble
        //act
        int id = 123456;
        parent.setId(id);
        //assert
        assertEquals(id, parent.getId(), "IDs should match");
    }

}
