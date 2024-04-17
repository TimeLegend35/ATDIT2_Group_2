package de.badwalden.schule.model.helper;

import de.badwalden.schule.dao.ParentDAO;
import de.badwalden.schule.dao.StudentDAO;
import de.badwalden.schule.model.Parent;
import de.badwalden.schule.model.Student;

import java.util.ArrayList;
import java.util.List;

public class ModelBuilder {

    public static Parent buildModelFromParent(int id) {
        // build base parent
        List<Object[]> results = ParentDAO.get(id);
        Parent parent = new Parent(id, results.get(0)[1].toString(), results.get(0)[2].toString());

        // build children
        results = StudentDAO.getStudentsFromParent(id);

        // Student List
        List<Student> chlidrenList = new ArrayList<>();
        Student newChild;

        for (Object[] row : results) {
            // this wont work!!!
            newChild = new Student((int) row[0], (String) row[1], (int) row[2], (boolean) row[3], (List) row[4]);
            chlidrenList.add(newChild);
        }

        // Link children to base parent
        parent.setChildren(chlidrenList);

        return parent;
    }

    public static Student buildModelFromStudent(int id) {
        // build base Student
        List<Object[]> results = StudentDAO.getStudent(id);
        Object[] resultStudent = results.get(0);
        Student student = new Student((int) resultStudent[0], (String) resultStudent[1], (int) resultStudent[2], (boolean) resultStudent[3], (List) resultStudent[4]);

        return student;
    }
}
