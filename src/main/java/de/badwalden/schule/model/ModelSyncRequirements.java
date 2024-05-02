package de.badwalden.schule.model;


/**
 * Interface defining requirements for model synchronization.
 */
public interface ModelSyncRequirements {
    /**
     * Updates the model in the database.
     */
    public void update();

    /**
     * Converts the model data into an object array for database operations.
     * @return an array of objects representing the model data
     */
    public Object[] toObjectArray();
}
