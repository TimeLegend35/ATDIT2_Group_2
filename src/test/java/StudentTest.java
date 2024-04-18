import de.badwalden.schule.model.Parent;
import de.badwalden.schule.model.Service;
import de.badwalden.schule.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    private Student student;

    @BeforeEach
    public void setUp(){
        student = new Student();
    }

    @Test
    public void testCompulsorySchooling(){
        //assemble
        boolean initialCompulsorySchooling = student.isCompulsorySchooling();
        //act
        student.setCompulsorySchooling(true);
        //assert
        assertTrue(student.isCompulsorySchooling(), "Compulsory schooling should be true after setting");
        assertNotEquals(initialCompulsorySchooling, student.isCompulsorySchooling(), "Compulsory schooling should have changed");
    }

    @Test
    public void testServiceList(){
        //assemble
        List<Service> serviceList = new ArrayList<>();
        Service fussball = new Service();
        Service basteln = new Service();
        //act
        serviceList.add(fussball);
        serviceList.add(basteln);
        student.setServiceList(serviceList);
        //assert
        assertEquals(serviceList, student.getServiceList(), "Service list should match after setting");
    }

}
