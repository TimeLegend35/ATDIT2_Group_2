package de.badwalden.schule.model;

import de.badwalden.schule.dao.DatabaseInteractions;

public interface ModelSyncRequirements {
    public void update();

    public Object[] toObjectArray();
}
