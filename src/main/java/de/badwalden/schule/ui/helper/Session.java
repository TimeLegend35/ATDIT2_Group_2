package de.badwalden.schule.ui.helper;

import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.model.User;

import java.util.List;

public class Session {
    private static Session instance;
    private User currentUser;
    private CareOffer cachedCareOffer;
    private List<CareOffer> cachedCareOfferList;

    // Private constructor to prevent instantiation from other classes
    public Session() {}

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

    public void setCachedCareOffer(CareOffer careOffer) {
        this.cachedCareOffer = careOffer;
    }

    public CareOffer getCachedCareOffer() {
        return this.cachedCareOffer;
    }

    public void setCachedCareOfferList(List<CareOffer> careOfferList) {
        this.cachedCareOfferList = careOfferList;
    }

    public List<CareOffer> getCachedCareOfferList() {
        return this.cachedCareOfferList;
    }

    // Method to get a CareOffer by ID from the cached list
    public CareOffer getCareOfferById(int id) {
        if (cachedCareOfferList != null) {
            for (CareOffer careOffer : cachedCareOfferList) {
                if (careOffer.getId() == id) {
                    return careOffer;
                }
            }
        }
        return null; // Return null if no CareOffer with the given ID is found
    }
}
