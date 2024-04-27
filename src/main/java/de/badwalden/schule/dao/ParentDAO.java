package de.badwalden.schule.dao;

import java.util.List;

public class ParentDAO implements DatabaseInteractions{
    @Override
    public List<Object[]> get(int parentId) throws RuntimeException {
        String sql = "SELECT * FROM parents WHERE parent_id = ?";
        List<Object[]> results = dbConnection.executeQuery(sql, new Object[]{parentId});

        // Check if exactly one parent is returned
        if (results.size() != 1) {
            throw new RuntimeException("Error: Expected one parent, found " + results.size());
        }

        return results;
    }


    @Override
    public void write(List<Object[]> targets) {
        // out of scope for use case
    }

}
