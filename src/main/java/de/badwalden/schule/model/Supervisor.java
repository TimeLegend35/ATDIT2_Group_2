package de.badwalden.schule.model;

import java.util.List;


/**
 * Represents a supervisor, extending the User class, with specific services they offer.
 */
public class Supervisor extends User {
    private List<Service> offeredServices;

    /**
     * Constructs a Supervisor with the specified ID and name.
     *
     * @param id   the unique identifier of the supervisor
     * @param name the name of the supervisor
     */
    public Supervisor(int id, String name) {
        super();

        this.setId(id);
        this.setFirstName(name);
    }

}
