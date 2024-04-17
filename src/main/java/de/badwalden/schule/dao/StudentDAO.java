package de.badwalden.schule.dao;

import java.util.List;

public class StudentDAO {
    public static List<Object[]> getStudentsFromParent(int parentId) {
        List<Object[]> results = DBConnector.executeQuery("");

        return results;
    }

    public static List<Object[]> getStudent(int id) {
        List<Object[]> results = DBConnector.executeQuery("");

        // only one parent should be returned
        if (results.size() != 1) {
            // throw exception
            System.out.println("Error: More than one student!!! throw exception here");
        }

        return results;
    }
}
