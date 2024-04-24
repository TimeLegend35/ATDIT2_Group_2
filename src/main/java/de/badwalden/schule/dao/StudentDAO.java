package de.badwalden.schule.dao;

import java.util.List;

public class StudentDAO implements DatabaseInteractions{
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
}
