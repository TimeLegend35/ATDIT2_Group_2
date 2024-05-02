package de.badwalden.schule.dao;

import de.badwalden.schule.exception.UnexpectedResultsException;
import de.badwalden.schule.ui.helper.LanguageHelper;
import java.util.List;
import java.util.logging.Logger;


/**
 * Data Access Object (DAO) for managing parent data in the database.
 * Implements DatabaseInteractions interface to provide database operation methods.
 */
public class ParentDAO implements DatabaseInteractions {

    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());

    /**
     * Retrieves a parent's information from the database using the parent's ID.
     * Throws UnexpectedResultsException if the number of results returned is not exactly one.
     *
     * @param parentId the ID of the parent to retrieve
     * @return a list containing a single Object array with the parent's data
     * @throws UnexpectedResultsException if the number of parents found is not one
     */
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

    /**
     * This method is not implemented and will always return 0.
     * Intended for future use to handle database write operations for parent data.
     *
     * @param targets list of parent data entries to be written to the database
     * @return always returns 0
     */
    @Override
    public int write(List<Object[]> targets) {
        // out of scope for use case

        return 0;
    }

}
