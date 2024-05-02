package de.badwalden.schule.dao;

import de.badwalden.schule.exception.UnexpectedResultsException;
import de.badwalden.schule.ui.helper.LanguageHelper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Data Access Object (DAO) for managing care offer data in the database.
 * Implements DatabaseInteractions to provide specific operations for care offers.
 */
public class CareOfferDAO implements DatabaseInteractions {
    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());

    /**
     * Retrieves a care offer from the database using its unique identifier.
     * Throws UnexpectedResultsException if the query does not return exactly one care offer.
     *
     * @param careOfferId the unique identifier of the care offer to retrieve
     * @return a list containing a single Object array representing the care offer data
     * @throws UnexpectedResultsException if the number of care offers retrieved is not one
     */
    @Override
    public List<Object[]> get(int careOfferId) throws UnexpectedResultsException {

        String sql = "SELECT * FROM care_offers WHERE care_offer_id = ?";
        List<Object[]> results = dbConnection.executeQuery(sql, new Object[]{careOfferId});

        // only one care offer should be returned
        if (results.size() != 1) {
            // throw exception
            throw new UnexpectedResultsException(LanguageHelper.getString("unexpected_size"), 1, results.size());
        }

        return results;
    }

    /**
     * Updates information for multiple care offers in the database.
     * Tracks and returns the number of database rows affected by the update operations.
     *
     * @param targets list of care offer data entries to update
     * @return the number of rows affected by the update operations
     */
    @Override
    public int write(List<Object[]> targets) {
        int executionCounter = 0;

        for (Object[] careOffer : targets) {
            String sql = "UPDATE care_offers SET " +
                    "supervisor_employee_number = ?, " +
                    "oldest_class_level = ?, " +
                    "youngest_class_level = ?, " +
                    "care_offer_name = ?, " +
                    "description = ?, " +
                    "places_available = ? " +
                    "WHERE care_offer_id = ?";
            Object[] params = new Object[]{
                    careOffer[0],  // supervisor_employee_number
                    careOffer[1],  // oldest_class_level
                    careOffer[2],  // youngest_class_level
                    careOffer[3],  // care_offer_name
                    careOffer[4],  // description
                    careOffer[5],  // places_available
                    careOffer[6]   // care_offer_id
            };

            executionCounter += dbConnection.executeUpdate(sql, params);
            logger.log(Level.INFO, LanguageHelper.getString("excecute_update"));
        }

        return executionCounter;
    }

    /**
     * Retrieves all care offer IDs associated with a given student.
     *
     * @param studentId the unique identifier of the student
     * @return a list of Object arrays, each containing a care offer ID associated with the student
     */
    public List<Object[]> getCareOffersIdsForStudent(int studentId) {
        String sql = """
                 SELECT co.care_offer_id
                 FROM care_offers co
                 JOIN child_care_offer_assignment cca ON co.care_offer_id = cca.care_offer_id
                 WHERE cca.student_id = ?
                 """;

        List<Object[]> results = dbConnection.executeQuery(sql, new Object[]{studentId});

        return results;
    }

    /**
     * Retrieves all care offers from the database.
     * Throws UnexpectedResultsException if no care offers are found.
     *
     * @return a list of Object arrays, each representing a row of care offer data
     * @throws UnexpectedResultsException if no care offers are present
     */
    public List<Object[]> getAllCareOffers() throws UnexpectedResultsException {
        String sql = "SELECT * FROM care_offers";
        List<Object[]> results = dbConnection.executeQuery(sql, new Object[]{});

        // Check if no care offers are present
        if (results.isEmpty()) {
            throw new UnexpectedResultsException(LanguageHelper.getString("empty_result"), 5, 0);
        }

        return results;
    }

}
