package de.badwalden.schule.model.helper;

import de.badwalden.schule.dao.CareOfferDAO;
import de.badwalden.schule.dao.DBConnector;
import de.badwalden.schule.dao.ParentDAO;
import de.badwalden.schule.dao.StudentDAO;
import de.badwalden.schule.exception.SessionDataNotLoaded;
import de.badwalden.schule.exception.UnexpectedResultsException;
import de.badwalden.schule.model.*;
import de.badwalden.schule.ui.helper.LanguageHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Utility class for building complex models involving multiple entities such as parents, students, and care offers.
 * This class retrieves and processes data using DAOs and caches it for session-long use.
 */
public class ModelBuilder {
    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());
    private static Session session = Session.getInstance();
    private static final ParentDAO parentDao = new ParentDAO();
    private static final StudentDAO studentDao = new StudentDAO();
    private static final CareOfferDAO careOfferDao = new CareOfferDAO();


    /**
     * Initializes and caches necessary data for the session, particularly care offers.
     */
    private static void buildSessionData() {

        // fill careOffers for Session
        try {
            // pull multiple access object like CareOffers
            List<Object[]> results = careOfferDao.getAllCareOffers();

            // fill care offers into session to keep in runtime
            List<CareOffer> careOfferList = new ArrayList<>();

            for (Object[] row : results) {
                careOfferList.add(buildCareOffer(row));
            }

            session.setCachedCareOfferList(careOfferList);

        } catch (UnexpectedResultsException e) {
            logger.log(Level.SEVERE, LanguageHelper.getString("error_fetch_co") +
                    e.getWantedCount() + LanguageHelper.getString("got") + e.getRealCount(), e);
        }
    }

    /**
     * Constructs a complete parent model with all related children and their associated data.
     *
     * @param id the parent's unique identifier
     * @return a fully constructed Parent object
     */
    public static Parent buildModelFromParent(int id) {
        // incase logout happened
        session = Session.getInstance();

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
     * Constructs a complete student model including associated services and details.
     *
     * @param id the student's unique identifier
     * @return a fully constructed Student object
     */
    public static Student buildModelFromStudent(int id) {
        // incase logout happened
        session = Session.getInstance();

        // initialize needed data for Session
        buildSessionData();

        return buildStudent(id);
    }

    /**
     * Retrieves and builds a parent object from the database.
     *
     * @param parentId the unique identifier of the parent
     * @return a Parent object or null if an error occurs
     */
    private static Parent buildParent(int parentId) {
        try {
            // build base parent
            List<Object[]> results = parentDao.get(parentId);
            String firstName = (String) results.get(0)[1];
            String lastName = (String) results.get(0)[2];
            String residence = (String) results.get(0)[3];

            return new Parent(parentId, firstName, lastName, residence);
        } catch (UnexpectedResultsException e) {
            logger.log(Level.SEVERE, LanguageHelper.getString("error_fetch_parent") +
                    e.getWantedCount() + LanguageHelper.getString("got") + e.getRealCount(), e);
            return null;
        }
    }

    /**
     * Retrieves and builds a student object from the database.
     *
     * @param studentId the unique identifier of the student
     * @return a Student object or null if an error occurs
     */
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

            Student student = new Student(studentId, class_year, firstName, lastName, age,
                    compulsorySchooling, rightOfService, serviceList);
            return student;

        } catch (UnexpectedResultsException e) {
            logger.log(Level.SEVERE, LanguageHelper.getString("error_fetch_student") +
                    e.getWantedCount() + LanguageHelper.getString("got") + e.getRealCount(), e);
            return null;
        } catch (SessionDataNotLoaded e2) {
            logger.log(Level.SEVERE, LanguageHelper.getString("Missing_session_data") +
                    e2.getDataPart(), e2);
            return null;
        }
    }

    /**
     * Constructs a CareOffer object from an array of objects representing database row data.
     *
     * @param row an array of objects representing a row of care offer data
     * @return a fully constructed CareOffer object
     */
    private static CareOffer buildCareOffer(Object[] row) {
        logger.log(Level.INFO, LanguageHelper.getString("build_co") + " " +
                row[0].toString() + " " + row[1].toString() + " " + row[2].toString() + " " +
                row[3].toString() + " " + row[4].toString() + " " + row[5].toString() + " " +
                row[6].toString());

        int id = (int) row[0];
        int supervisorId = (int) row[1];
        Supervisor supervisor = new Supervisor(supervisorId, "MasterSupervisor");
        int oldestClassLevel = (int) row[2];
        int youngestClassLevel = (int) row[3];
        String careOfferName = (String) row[4];
        String description = (String) row[5];
        int seatsAvailable = (int) row[6];

        return new CareOffer(id, supervisor, oldestClassLevel, youngestClassLevel, careOfferName,
                description, seatsAvailable);
    }

    /**
     * Constructs a list of services (care offers) available to a specific student.
     * Throws SessionDataNotLoaded if care offers have not been cached.
     *
     * @param studentId the unique identifier of the student
     * @return a list of Service objects
     * @throws SessionDataNotLoaded if the required session data has not been loaded
     */
    private static List<Service> buildServiceListForStudent(int studentId) throws SessionDataNotLoaded {
        // check if careOffers are loaded
        if (session.getCachedCareOfferList() == null) {
            throw new SessionDataNotLoaded(LanguageHelper.getString("build_service_list_student"), "CareOfferList");
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
