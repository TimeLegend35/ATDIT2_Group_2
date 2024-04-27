package de.badwalden.schule.dao;

import java.util.List;

public class StudentDAO implements DatabaseInteractions{
    @Override
    public List<Object[]> get(int id) throws RuntimeException {
        String sql = "SELECT * FROM children WHERE student_id = ?";
        List<Object[]> results = dbConnection.executeQuery(sql, new Object[]{id});

        // Check if exactly one student is returned
        if (results.size() != 1) {
            throw new RuntimeException("Error: Expected one student, found " + results.size());
        }

        return results;
    }

    @Override
    public void write(List<Object[]> targets) {
        // out of scope for use case
    }

    public List<Object[]> getStudentsIdFromParent(int parentId) throws RuntimeException {
        String sql = """
                 SELECT child_id
                 FROM parent_child_assignment pca
                 WHERE pca.parent_id = ?
                 """;
        List<Object[]> results = dbConnection.executeQuery(sql, new Object[]{parentId});
        return results;
    }

    public boolean removeChildFromCareOffer(int student_ID, int careOffer_ID) throws RuntimeException {
        String sql = "DELETE FROM child_care_offer_assignment WHERE student_id = ? AND care_offer_id = ?";
        try {
            dbConnection.executeUpdate(sql, new Object[]{student_ID, careOffer_ID});
            return true;
        } catch (Exception e) {
            System.err.println("Remove Child From Care Offer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean addChildToCareOffer(int student_ID, int careOffer_ID) throws RuntimeException {
        String sql = "INSERT INTO child_care_offer_assignment (student_id, care_offer_id) VALUES (?, ?)";
        try {
            dbConnection.executeUpdate(sql, new Object[]{student_ID, careOffer_ID});
            return true;
        } catch (Exception e) {
            System.err.println("Add Child to Care Offer: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
