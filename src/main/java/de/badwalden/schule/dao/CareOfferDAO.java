package de.badwalden.schule.dao;

import java.util.List;


public class CareOfferDAO implements DatabaseInteractions {

    @Override
    public List<Object[]> get(int careOfferId) {

        List<Object[]> results = dbConnection.executeQuery("Select * FROM care_offers WHERE care_offer_id=" + careOfferId);

        // only one care offer should be returned
        if (results.size() != 1) {
            // throw exception
            System.out.println("Error: More than one care offer!!! throw exception here");
        }

        return results;
    }

    @Override
    public void write(List<Object[]> targets) {
        for (Object[] careOffer : targets) {
            /// SQL statement for updating entries in the care_offers table
            String sql = "UPDATE care_offers SET " +
                    "supervisor_employee_number = " + careOffer[0] + ", " +
                    "oldest_class_level = " + careOffer[1] + ", " +
                    "youngest_class_level = " + careOffer[2] + ", " +
                    "care_offer_name = '" + careOffer[3] + "', " +
                    "description = '" + careOffer[4] + "', " +
                    "places_available = " + careOffer[5] +
                    " WHERE care_offer_id = " + careOffer[6];

            System.out.println(sql);
            dbConnection.executeUpdate(sql);
        }
    }

    public List<Object[]> getCareOffersIdsForStudent(int studentId) {
        // edit SQL query to fit database ERM
        List<Object[]> results = dbConnection.executeQuery(
                """
                        SELECT co.care_offer_id
                        FROM care_offers co
                        JOIN child_care_offer_assignment cca ON co.care_offer_id = cca.care_offer_id
                        WHERE cca.student_id = 
                        """ + studentId
        );

        // only one care offer should be returned
        if (results.isEmpty()) {
            // throw exception
            System.out.println("No care offer found for this student!");
        }

        return results;
    }

    public List<Object[]> getAllCareOffers() {
        // edit SQL query to fit database ERM
        List<Object[]> results = dbConnection.executeQuery("SELECT * from care_offers");

        // only one care offer should be returned
        if (results.isEmpty()) {
            // throw exception
            System.out.println("Error: No care offers in db!");
        }

        return results;
    }

}
