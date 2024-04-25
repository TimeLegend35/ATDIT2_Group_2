package de.badwalden.schule.dao;

import java.util.List;

public class StudentDAO implements DatabaseInteractions{
    @Override
    public List<Object[]> get(int id) {
        List<Object[]> results = dbConnection.executeQuery("SELECT * FROM children WHERE student_id = " + id);

        // only one parent should be returned
        if (results.size() != 1) {
            // throw exception
            System.out.println("Error: More than one student!!! throw exception here");
        }

        return results;
    }

    @Override
    public void write(List<Object[]> targets) {

    }

    public List<Object[]> getStudentsIdFromParent(int parentId) {
        List<Object[]> results = dbConnection.executeQuery(
                """
                        SELECT child_id
                        FROM parent_child_assignment pca
                        WHERE pca.parent_id = 
                    """ + parentId
        );

        return results;
    }

    public boolean removeChildFromCareOffer(int student_ID, int careOffer_ID) {
        try {
            dbConnection.executeUpdate("DELETE FROM child_care_offer_assignment WHERE student_id = " + student_ID + " AND care_offer_id = " + careOffer_ID);
            return true;
        } catch (Exception e) {
            System.err.println("Remove Child From Care Offer" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean addChildtoCareoffer(int student_ID, int careOffer_ID) {
        try {
            dbConnection.executeUpdate("Insert Into child_care_offer_assignment (student_id, care_offer_id) Values (" + student_ID + "," + careOffer_ID + ")");
            return true;
        } catch (Exception e) {
            System.err.println("add Child From Care Offer" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
