package de.badwalden.schule.dao;

import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.model.Teacher;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class CareOfferDAO implements DatabaseInteractions{

    public static List<Object[]> getCareOffer(int careOfferId) {
        // edit SQL query to fit database ERM
        List<Object[]> results = dbConnection.executeQuery("Select * FROM care_offers WHERE id=" + careOfferId);

        // only one care offer should be returned
        if (results.size() != 1) {
            // throw exception
            System.out.println("Error: More than one care offer!!! throw exception here");
        }

        return results;
    }

    public static List<Object[]> getCareOffersForStudent(int studentId) {
        // edit SQL query to fit database ERM
        List<Object[]> results = dbConnection.executeQuery("");

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

    // Can be deleted / MOCKED. Real way would be to call getAllCareOffers() from the CareOfferController for getting
    // the careOffers
    public static ObservableList getAll(){
        ObservableList<CareOffer> careOffers = FXCollections.observableArrayList();
        //Example Care Offer (mocked)
        CareOffer careOffer1 = new CareOffer(1, "", "", 0, 0, 0);
        careOffer1.setId(1);
        careOffer1.setName("Sample Service");
        careOffer1.setDescription("This is a sample service description.");
        careOffer1.setNumberOfSeats(12);
        careOffer1.setYoungestGrade(1);
        careOffer1.setOldestGrade(2);
        careOffer1.setMainSupervisor(new Teacher());


        careOffers.add(careOffer1);

        return careOffers;
    }

}
