package de.badwalden.schule.dao;

import de.badwalden.schule.exception.UnexpectedResultsException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO implements DatabaseInteractions{
    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());
    @Override
    public List<Object[]> get(int id) throws UnexpectedResultsException {
        String sql = "SELECT * FROM children WHERE student_id = ?";
        List<Object[]> results = dbConnection.executeQuery(sql, new Object[]{id});

        // Check if exactly one student is returned
        if (results.size() != 1) {
            throw new UnexpectedResultsException("Error: Expected one student, found " + results.size(), 1, results.size());
        }

        return results;
    }

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
            logger.log(Level.INFO, "Student Update wrote to Database");
        }

        return executionCounter;
    }

    public List<Object[]> getStudentsIdFromParent(int parentId) {
        String sql = """
                 SELECT child_id
                 FROM parent_child_assignment pca
                 WHERE pca.parent_id = ?
                 """;

        List<Object[]> results = dbConnection.executeQuery(sql, new Object[]{parentId});

        return results;
    }

    public void removeChildFromCareOffer(int student_ID, int careOffer_ID) {
        String sql = "DELETE FROM child_care_offer_assignment WHERE student_id = ? AND care_offer_id = ?";

        dbConnection.executeUpdate(sql, new Object[]{student_ID, careOffer_ID});
    }

    public void addChildToCareOffer(int student_ID, int careOffer_ID) {
        String sql = "INSERT INTO child_care_offer_assignment (student_id, care_offer_id) VALUES (?, ?)";

        dbConnection.executeUpdate(sql, new Object[]{student_ID, careOffer_ID});
    }
}
