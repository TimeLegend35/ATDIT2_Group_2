package de.badwalden.schule.dao;

import java.util.List;

public class StudentDAO implements DatabaseInteractions{
    public static List<Object[]> getStudentsFromParent(int parentId) {
        List<Object[]> results = dbConnection.executeQuery(
                """
                        SELECT *
                        FROM parent_child_assignment pca
                        JOIN children ch ON pca.child_id = ch.student_id
                        WHERE pca.parent_id = 
                    """ + parentId
        );

        return results;
    }

    public static List<Object[]> getStudent(int id) {
        List<Object[]> results = dbConnection.executeQuery("SELECT * FROM children WHERE student_id = " + id);

        // only one parent should be returned
        if (results.size() != 1) {
            // throw exception
            System.out.println("Error: More than one student!!! throw exception here");
        }

        return results;
    }
}
