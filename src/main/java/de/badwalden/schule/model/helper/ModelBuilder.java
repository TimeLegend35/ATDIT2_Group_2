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
        String firstName = (String) results.get(0)[1];
        String lastName = (String) results.get(0)[2];
        String residence = (String) results.get(0)[3];

        Parent parent = new Parent(id, firstName, lastName, residence);

        // build children
        results = StudentDAO.getStudentsFromParent(id);

        // Student List
        List<Student> chlidrenList = new ArrayList<>();
        Student newChild;

        for (Object[] row : results) {
            // this wont work!!!
            id = (int) row[0];
            chlidrenList.add(buildModelFromStudent(id));
        }

        // Link children to base parent
        parent.setChildren(chlidrenList);

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

        int class_year = (int) resultStudent[1];
        String firstName = (String) resultStudent[2];
        String lastName = (String) resultStudent[3];
        int age = (int) resultStudent[4];
        boolean compulsorySchooling = (boolean) resultStudent[5];
        boolean rightOfService = (boolean) resultStudent[6];
        List<Service> serviceList = buildServiceListForStudent(id);
        Student student = new Student(id, class_year, firstName, lastName, age, compulsorySchooling, rightOfService, serviceList);

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

    private static List<Service> buildServiceListForStudent(int studentId) {
        // base data
        List<Object[]> results = CareOfferDAO.getCareOffersForStudent(studentId);

        // create care offers
        List<Service> careOffers = new ArrayList<>();
        Service newCareOffer;

        for (Object[] row : results) {

            newCareOffer = new CareOffer((int) row[0], (String) row[1], (String) row[2], (int) row[3], (int) row[4], (int) row[5]);
            careOffers.add(newCareOffer);
        }

        return careOffers;
    }
}
