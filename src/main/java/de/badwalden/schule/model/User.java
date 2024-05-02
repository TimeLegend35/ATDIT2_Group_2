package de.badwalden.schule.model;

import de.badwalden.schule.model.outOfScope.Notification;

import java.util.Calendar;
import java.util.List;

/**
 * Abstract base class for user types, providing common properties such as name and residence.
 */
public abstract class User {
    private int id;
    private String first_name;
    private String last_name;
    private Calendar birthday;
    private String residence;
    private List<Notification> notifications;

    /**
     * Default constructor.
     */
    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String firstName) {
        this.first_name = firstName;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getResidence() {
        return residence;
    }
}
