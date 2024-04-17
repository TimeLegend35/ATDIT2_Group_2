package de.badwalden.schule.model.helper;

import de.badwalden.schule.dao.CareOfferDAO;
import de.badwalden.schule.dao.ParentDAO;
import de.badwalden.schule.dao.StudentDAO;
import de.badwalden.schule.model.*;

import java.util.ArrayList;
import java.util.List;

public class ModelBuilder {

    /**
     * A function to build the data model starting from a parent.
     *
     * @param  id  the ID of the parent to build the model from
     * @return     the Parent instance with the data
     */
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

        for (Student child : chlidrenList) {
            // build care offers
            results = CareOfferDAO.getCareOffersForStudent(child.getId());

            // CareOffer List
            CareOffer newCareOffer;

            for (Object[] row : results) {
                // this wont work!!!
                newCareOffer = new CareOffer((int) row[0], (String) row[1], (String) row[2], (int) row[3], (int) row[4], (int) row[5]);
                child.getServiceList().add(newCareOffer);
            }
        }

        return parent;
    }

    /**
     * A function to build the data model starting from a student.
     *
     * @param  id  the ID of the student to build the model from
     * @return     the Student instance with the data
     */
    public static Student buildModelFromStudent(int id) {

        // build base Student
        List<Object[]> results = StudentDAO.getStudent(id);
        Object[] resultStudent = results.get(0);
        Student student = new Student((int) resultStudent[0], (String) resultStudent[1], (int) resultStudent[2], (boolean) resultStudent[3], (List<Service>) resultStudent[4]);

        results = CareOfferDAO.getCareOffersForStudent(student.getId());

        // CareOffer List
        CareOffer newCareOffer;

        for (Object[] row : results) {
            // this wont work!!!
            newCareOffer = new CareOffer((int) row[0], (String) row[1], (String) row[2], (int) row[3], (int) row[4], (int) row[5]);
            student.getServiceList().add(newCareOffer);
        }

        return student;
    }
}
