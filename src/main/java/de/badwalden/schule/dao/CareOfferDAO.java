package de.badwalden.schule.dao;

import java.util.List;


public class CareOfferDAO implements DatabaseInteractions {

    @Override
    public List<Object[]> get(int careOfferId) throws RuntimeException {
        String sql = "SELECT * FROM care_offers WHERE care_offer_id = ?";
        List<Object[]> results = dbConnection.executeQuery(sql, new Object[]{careOfferId});

        // only one care offer should be returned
        if (results.size() != 1) {
            // throw exception
            throw new RuntimeException("Error: More than one or no care offer found!");
        }

        return results;
    }

    @Override
    public void write(List<Object[]> targets) {
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

            dbConnection.executeUpdate(sql, params);
        }
    }

    public List<Object[]> getCareOffersIdsForStudent(int studentId) throws RuntimeException {
        String sql = """
                 SELECT co.care_offer_id
                 FROM care_offers co
                 JOIN child_care_offer_assignment cca ON co.care_offer_id = cca.care_offer_id
                 WHERE cca.student_id = ?
                 """;
        List<Object[]> results = dbConnection.executeQuery(sql, new Object[]{studentId});

        // Check if no care offer was returned
        if (results.isEmpty()) {
            throw new RuntimeException("No care offer found for this student!");
        }

        return results;
    }

    public List<Object[]> getAllCareOffers() throws RuntimeException {
        String sql = "SELECT * FROM care_offers";
        List<Object[]> results = dbConnection.executeQuery(sql, new Object[]{});

        // Check if no care offers are present
        if (results.isEmpty()) {
            throw new RuntimeException("Error: No care offers in database!");
        }

        return results;
    }


}
