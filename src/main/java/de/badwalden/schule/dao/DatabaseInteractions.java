package de.badwalden.schule.dao;

import de.badwalden.schule.exception.UnexpectedResultsException;

import java.util.List;

/**
 * Interface defining common database operations for data access objects.
 */
public interface DatabaseInteractions {
    DBConnector dbConnection = DBConnector.getInstance();

    /**
     * Retrieves data based on an identifier.
     *
     * @param id the unique identifier for the query
     * @return a list of Object arrays representing the data rows retrieved
     * @throws UnexpectedResultsException if the result does not meet expectations
     */
    public List<Object[]> get(int id) throws UnexpectedResultsException;

    /**
     * Writes or updates a list of data entries in the database.
     *
     * @param targets the data entries to write or update
     * @return the number of entries successfully written or updated
     */
    public int write(List<Object[]> targets);

}

