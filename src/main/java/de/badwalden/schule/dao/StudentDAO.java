package de.badwalden.schule.dao;

import de.badwalden.schule.exception.UnexpectedResultsException;
import de.badwalden.schule.ui.helper.LanguageHelper;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Access Object (DAO) for managing student data in the database.
 * Implements DatabaseInteractions interface to provide database operation methods specific to students.
 */
public class StudentDAO implements DatabaseInteractions {
    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());

    /**
     * Retrieves a student's information from the database using the student's ID.
     * Throws UnexpectedResultsException if the number of results returned is not exactly one.
     *
     * @param id the ID of the student to retrieve
     * @return a list containing a single Object array with the student's data
     * @throws UnexpectedResultsException if the number of students found is not one
     */
    @Override
    public List<Object[]> get(int id) throws UnexpectedResultsException {
        String sql = "SELECT * FROM children WHERE student_id = ?";
        List<Object[]> results = dbConnection.executeQuery(sql, new Object[]{id});

        // Check if exactly one student is returned
        if (results.size() != 1) {
            throw new UnexpectedResultsException(LanguageHelper.getString("return_one_student") + results.size(), 1, results.size());
        }

        return results;
    }

    /**
     * Updates student data in the database for each student in the provided list.
     * Returns the total number of database rows affected by the update operations.
     *
     * @param targets list of student data entries to update
     * @return the number of rows affected by the update operations
     */
    @Override
    public int write(List<Object[]> targets) {
        int executionCounter = 0;

        for (Object[] student : targets) {
            String sql = "UPDATE children SET " +
                    "class_year = ?, " +
                    "given_name = ?, " +
                    "surname = ?, " +
                    "age = ?, " +
                    "compulsory_school_attendance = ?, " +
                    "allowed_for_care_offers = ? " +
                    "WHERE student_id = ?";
            Object[] params = new Object[]{
                    student[1],  // class_year
                    student[2],  // given_name
                    student[3],  // surname
                    student[4],  // age
                    student[5],  // compulsory_school_attendance
                    student[6],  // allowed_for_care_offers
                    student[0]   // student_id (Primary Key)
            };

            executionCounter += dbConnection.executeUpdate(sql, params);
            logger.log(Level.INFO, LanguageHelper.getString("student_update_db"));
        }

        return executionCounter;
    }

    /**
     * Retrieves a list of student IDs associated with a given parent ID.
     *
     * @param parentId the ID of the parent whose children's IDs are to be fetched
     * @return a list of Object arrays, each containing a child's ID
     */
    public List<Object[]> getStudentsIdFromParent(int parentId) {
        String sql = """
                 SELECT child_id
                 FROM parent_child_assignment pca
                 WHERE pca.parent_id = ?
                 """;

        List<Object[]> results = dbConnection.executeQuery(sql, new Object[]{parentId});

        return results;
    }

    /**
     * Removes a student from a specified care offer in the database.
     *
     * @param student_ID the ID of the student to remove from the care offer
     * @param careOffer_ID the ID of the care offer from which the student is removed
     */
    public void removeStudentFromCareOffer(int student_ID, int careOffer_ID) {
        String sql = "DELETE FROM child_care_offer_assignment WHERE student_id = ? AND care_offer_id = ?";

        dbConnection.executeUpdate(sql, new Object[]{student_ID, careOffer_ID});
    }

    /**
     * Adds a student to a specified care offer in the database.
     *
     * @param student_ID the ID of the student to add to the care offer
     * @param careOffer_ID the ID of the care offer to which the student is added
     */
    public void addStudentToCareOffer(int student_ID, int careOffer_ID) {
        String sql = "INSERT INTO child_care_offer_assignment (student_id, care_offer_id) VALUES (?, ?)";

        dbConnection.executeUpdate(sql, new Object[]{student_ID, careOffer_ID});
    }
}
