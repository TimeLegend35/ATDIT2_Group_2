package de.badwalden.schule.dao;

import java.util.List;

public class ParentDAO implements DatabaseInteractions{
    public static List<Object[]> get(int parentId) {
        // edit SQL query to fit database ERM
        List<Object[]> results = dbConnection.executeQuery("Select * FROM parents WHERE parent_id = " + parentId);

        // only one parent should be returned
        if (results.size() != 1) {
            // throw exception
            System.out.println("Error: More or less than one parent!!! throw exception here");
        }

        return results;
    }

    public static boolean write() {
        return false;
    }

}
