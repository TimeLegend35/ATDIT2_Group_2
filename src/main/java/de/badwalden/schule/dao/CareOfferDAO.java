package de.badwalden.schule.dao;

import java.util.List;


public class CareOfferDAO implements DatabaseInteractions {

    public static List<Object[]> get(int careOfferId) {
        // edit SQL query to fit database ERM
        List<Object[]> results = dbConnection.executeQuery("Select * FROM care_offers WHERE care_offer_id=" + careOfferId);

        // only one care offer should be returned
        if (results.size() != 1) {
            // throw exception
            System.out.println("Error: More than one care offer!!! throw exception here");
        }

        return results;
    }

    public static boolean write() {
        return false;
    }

    public static List<Object[]> getCareOffersIdForStudent(int studentId) {
        // edit SQL query to fit database ERM
        List<Object[]> results = dbConnection.executeQuery(
                    """
                            SELECT care_offer_id
                            FROM care_offers co
                            RIGHT JOIN child_care_offer_assignment cca ON co.care_offer_id = cca.care_offer_id
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

    public static List<Object[]> getAllCareOffers() {
        // edit SQL query to fit database ERM
        List<Object[]> results = dbConnection.executeQuery("SELECT * from care_offers");

        // only one care offer should be returned
        if (results.isEmpty()) {
            // throw exception
            System.out.println("Error: No care offers in db!");
        }

        return results;
    }


    public static void removeChildFromCareOffer(int student_ID, int careOffer_ID) {
        try {
            dbConnection.executeUpdate("DELETE FROM child_care_offer_assignment WHERE student_id = " + student_ID + " AND care_offer_id = " + careOffer_ID);

        } catch (Exception e) {
            System.err.println("Remove Child From Care Offer" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void addChildtoCareoffer(int student_ID, int careOffer_ID) {
        try {
            dbConnection.executeUpdate("Insert Into child_care_offer_assignment (student_id, care_offer_id) Values (" + student_ID + "," + careOffer_ID + ")");

        } catch (Exception e) {
            System.err.println("add Child From Care Offer" + e.getMessage());
            e.printStackTrace();
        }
    }


}
