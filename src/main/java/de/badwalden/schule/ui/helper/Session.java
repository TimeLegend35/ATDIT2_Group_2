package de.badwalden.schule.ui.helper;

import de.badwalden.schule.model.User;

public class Session {
    private static Session instance;
    private User currentUser;

    // Private constructor to prevent instantiation from other classes
    private Session() {}

    // Method to get the instance of the class
    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    // Method to set the current user
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    // Method to get the current user
    public User getCurrentUser() {
        return currentUser;
    }
}