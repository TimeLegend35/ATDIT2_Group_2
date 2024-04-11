package de.badwalden.schule.model;

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
}
