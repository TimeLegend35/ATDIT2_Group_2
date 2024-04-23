package model;

import de.badwalden.schule.model.Service;
import de.badwalden.schule.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServiceTest {

    private Student bob;
    private Service basteln;


    @BeforeEach
    public void setUp(){
        bob = new Student();
        basteln = new Service();
    }

    @Test
    public void testAddStudentToStudentList(){
        //assemble
        //act
        basteln.setNumberOfSeats(2);
        basteln.addStudentToStudentList(bob);
        //assert
        assertTrue(basteln.getStudentList().contains(bob));
    }

    @Test
    public void testAddStudentWhenServiceIsFull(){
        //assemble
        Student clemens = new Student();
        Student dunja = new Student();
        //act
        basteln.setNumberOfSeats(2);
        basteln.addStudentToStudentList(clemens);
        basteln.addStudentToStudentList(dunja);
        basteln.addStudentToStudentList(bob);
        //assert
        assertFalse(basteln.getStudentList().contains(bob));
    }

}
