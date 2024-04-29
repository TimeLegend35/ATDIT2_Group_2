package de.badwalden.schule.dao;

import de.badwalden.schule.exception.UnexpectedResultsException;

import java.util.List;

public interface DatabaseInteractions {
    DBConnector dbConnection = DBConnector.getInstance();

    public List<Object[]> get(int id) throws UnexpectedResultsException;

    public void write(List<Object[]> targets);
}
