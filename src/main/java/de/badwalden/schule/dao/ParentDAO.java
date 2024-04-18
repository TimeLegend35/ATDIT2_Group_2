package de.badwalden.schule.dao;

import java.util.List;
import java.util.Objects;

public class ParentDAO implements DatabaseInteractions{

    public static List<Object[]> get(int parentId) {
        // edit SQL query to fit database ERM
        List<Object[]> results = dbConnection.executeQuery("Select * FROM parents WHERE parent_id = " + parentId);

        System.out.println("ParentDAO: "+ results);

        // only one parent should be returned
        if (results.size() != 1) {
            // throw exception
            System.out.println("Error: More or less than one parent!!! throw exception here");
        }

        return results;
    }

}
