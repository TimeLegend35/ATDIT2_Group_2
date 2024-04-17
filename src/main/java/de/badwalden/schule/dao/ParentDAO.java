package de.badwalden.schule.dao;

import java.util.List;
import java.util.Objects;

public class ParentDAO {

    public static List<Object[]> get(int id) {
        // edit SQL query to fit database ERM
        List<Object[]> results = DBConnector.executeQuery("Select * FROM parents WHERE id=" + id);

        // only one parent should be returned
        if (results.size() != 1) {
            // throw exception
            System.out.println("Error: More than one parent!!! throw exception here");
        }

        return results;
    }

}
