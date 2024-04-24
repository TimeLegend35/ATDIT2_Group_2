package de.badwalden.schule.dao;

import java.util.List;

public interface DatabaseInteractions {
    DBConnector dbConnection = DBConnector.getInstance();

    public List<Object[]> get(int id);

    public void write(List<Object[]> targets);
}
