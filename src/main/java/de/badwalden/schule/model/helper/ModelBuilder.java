package de.badwalden.schule.model.helper;

import de.badwalden.schule.dao.CareOfferDAO;
import de.badwalden.schule.dao.DBConnector;
import de.badwalden.schule.dao.ParentDAO;
import de.badwalden.schule.dao.StudentDAO;
import de.badwalden.schule.exception.SessionDataNotLoaded;
import de.badwalden.schule.exception.UnexpectedResultsException;
import de.badwalden.schule.model.*;
import de.badwalden.schule.ui.helper.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelBuilder {
    final static private Logger logger = Logger.getLogger(DBConnector.class.getName());
    final static private Session session = Session.getInstance();
    final static private ParentDAO parentDao = new ParentDAO();
    final static private StudentDAO studentDao = new StudentDAO();
    final static private CareOfferDAO careOfferDao = new CareOfferDAO();

    private static void buildSessionData() {
        // fill careOffers for Session
        try {
            // pull multiple access object like CareOffers
            List<Object[]> results = careOfferDao.getAllCareOffers();

            // fill care offers into session to keep in runtime
            List<CareOffer> careOfferList = new ArrayList<>();

            for (Object[] row: results) {
                careOfferList.add(buildCareOffer(row));
            }

            session.setCachedCareOfferList(careOfferList);

        } catch (UnexpectedResultsException e) {
            logger.log(Level.SEVERE, "Error fetching all care offers! expected: " + e.getWantedCount() + " got: " + e.getRealCount(), e);
        }
    }

    /**
     * A function to build the data model starting from a parent.
     *
     * @param  id  the ID of the parent to build the model from
     * @return     the Parent instance with the data
     */
    public static Parent buildModelFromParent(int id) {
        // initialize needed data for Session
        buildSessionData();

        // base parent
        Parent parent = buildParent(id);

        // build children
        List<Object[]> results = studentDao.getStudentsIdFromParent(id);

        // Student List
        List<Student> chlidrenList = new ArrayList<>();

        for (Object[] row : results) {
            id = (int) row[0];
            chlidrenList.add(buildStudent(id));
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
        // initialize needed data for Session
        buildSessionData();

        return buildStudent(id);
    }

    private static Parent buildParent(int parentId) {
        try {
            // build base parent
            List<Object[]> results = parentDao.get(parentId);
            String firstName = (String) results.get(0)[1];
            String lastName = (String) results.get(0)[2];
            String residence = (String) results.get(0)[3];

            return new Parent(parentId, firstName, lastName, residence);
        } catch (UnexpectedResultsException e) {
            logger.log(Level.SEVERE, "Error fetching parent! expected: " + e.getWantedCount() + " got: " + e.getRealCount(), e);
            return null;
        }
    }

    private static Student buildStudent(int studentId) {
        try {
            // build base Student
            List<Object[]> results = studentDao.get(studentId);
            Object[] resultStudent = results.get(0);

            int class_year = (int) resultStudent[1];
            String firstName = (String) resultStudent[2];
            String lastName = (String) resultStudent[3];
            int age = (int) resultStudent[4];
            boolean compulsorySchooling = (boolean) resultStudent[5];
            boolean rightOfService = (boolean) resultStudent[6];
            List<Service> serviceList = buildServiceListForStudent(studentId);

            Student student = new Student(studentId, class_year, firstName, lastName, age, compulsorySchooling, rightOfService, serviceList);
            return student;

        } catch (UnexpectedResultsException e) {
            logger.log(Level.SEVERE, "Error fetching student! expected: " + e.getWantedCount() + " got: " + e.getRealCount(), e);
            return null;
        } catch (SessionDataNotLoaded e2) {
            logger.log(Level.SEVERE, "Error creating Service List. Missing Data in Session: " + e2.getDataPart() , e2);
            return null;
        }
    }

    private static CareOffer buildCareOffer(Object[] row) {
        logger.log(Level.INFO, "ModelBuilder: Loaded Service: " + " " + row[0].toString() + " " +row[1].toString() + " " + row[2].toString() + " " + row[3].toString() + " " + row[4].toString() + " " + row[5].toString() + " " + row[6].toString());

        int id = (int) row[0];
        int supervisorId = (int) row[1];
        Supervisor supervisor = new Supervisor(supervisorId, "MasterSupervisor") ;
        int oldestClassLevel = (int) row[2];
        int youngestClassLevel = (int) row[3];
        String careOfferName = (String) row[4];
        String description = (String) row[5];
        int seatsAvailable = (int) row[6];

       return new CareOffer(id, supervisor, oldestClassLevel, youngestClassLevel, careOfferName, description, seatsAvailable);
    }

    private static List<Service> buildServiceListForStudent(int studentId) throws SessionDataNotLoaded {
        // check if careOffers are loaded
        if (session.getCachedCareOfferList() == null) {
            throw new SessionDataNotLoaded("Session Data not completely loaded! Cannot link Student to services", "CareOfferList");
        }

        // get care offer ids for student
        List<Object[]> results = careOfferDao.getCareOffersIdsForStudent(studentId);

        // create care offers
        List<Service> careOffers = new ArrayList<>();

        for (Object[] row : results) {
            int careOfferId = (int) row[0];
            for (CareOffer cachedCareOffer : session.getCachedCareOfferList()) {
                if (careOfferId == cachedCareOffer.getId()) {
                    careOffers.add(cachedCareOffer);
                }
            }
        }

        return careOffers;
    }
}
