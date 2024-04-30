package de.badwalden.schule.dao;

import de.badwalden.schule.exception.UnexpectedResultsException;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CareOfferDAO implements DatabaseInteractions {
    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());

    @Override
    public List<Object[]> get(int careOfferId) throws UnexpectedResultsException {
        String sql = "SELECT * FROM care_offers WHERE care_offer_id = ?";
        List<Object[]> results = dbConnection.executeQuery(sql, new Object[]{careOfferId});

        // only one care offer should be returned
        if (results.size() != 1) {
            // throw exception
            throw new UnexpectedResultsException("Error: More than one or no care offer found!", 1, results.size());
        }

        return results;
    }

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
            logger.log(Level.INFO, "Care Offer Update wrote to Database");
        }

        return executionCounter;
    }

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

    public List<Object[]> getAllCareOffers() throws UnexpectedResultsException {
        String sql = "SELECT * FROM care_offers";
        List<Object[]> results = dbConnection.executeQuery(sql, new Object[]{});

        // Check if no care offers are present
        if (results.isEmpty()) {
            throw new UnexpectedResultsException("Error: No care offers in database!", 10, 0);
        }

        return results;
    }

}
