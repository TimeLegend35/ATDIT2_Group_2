package de.badwalden.schule.model;

import de.badwalden.schule.model.outOfScope.Notification;

import java.util.Calendar;
import java.util.List;

public abstract class User {
    private int id;
    private String first_name;
    private Calendar birthday;
    private String residence;
    private List<Notification> notifications;

    public User() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName){
        this.first_name = firstName;
    }

    public String getFirstName() {
        return first_name;
    }
}
