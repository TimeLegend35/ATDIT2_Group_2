package de.badwalden.schule.dao;

import de.badwalden.schule.exception.UnexpectedResultsException;
import de.badwalden.schule.ui.helper.LanguageHelper;

import java.util.List;
import java.util.logging.Logger;

public class ParentDAO implements DatabaseInteractions{
    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());

    @Override
    public List<Object[]> get(int parentId) throws UnexpectedResultsException {
        String sql = "SELECT * FROM parents WHERE parent_id = ?";
        List<Object[]> results = dbConnection.executeQuery(sql, new Object[]{parentId});

        // Check if exactly one parent is returned
        if (results.size() != 1) {
            throw new UnexpectedResultsException(LanguageHelper.getString("return_one_parent") + results.size(), 1, results.size());
        }

        return results;
    }


    @Override
    public int write(List<Object[]> targets) {
        // out of scope for use case

        return 0;
    }

}
