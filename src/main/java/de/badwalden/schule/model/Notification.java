package de.badwalden.schule.model;

import java.util.Calendar;

public class Notification {
    private int id;
    private Calendar date;
    private String about;
    private String content;
    private User sender;
    private boolean read;

    public Notification() {

    }

}
