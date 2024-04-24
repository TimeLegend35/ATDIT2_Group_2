package de.badwalden.schule.model;

import java.util.List;

public class Supervisor extends User {
    private List<Service> offeredServices;

    public Supervisor(int id, String name) {
        super();

        this.setId(id);
        this.setFirstName(name);
    }

}
