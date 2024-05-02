package de.badwalden.schule.model.helper;

import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.model.User;

import java.util.List;

public class Session {
    private static Session instance;
    private User currentUser;
    private CareOffer cachedCareOffer;
    private List<CareOffer> cachedCareOfferList;

    // Private constructor to prevent instantiation from other classes
    public Session() {

    }

    /**
     * Returns the singleton instance of the Session class.
     *
     * @return the singleton instance of the Session class
     */
    public static synchronized Session getInstance() {
        if (instance == null) {
            instance = new Session();
        }
        return instance;
    }

    public synchronized void resetInstance() {
        instance = null;
    }

    // Method to set the current user
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * Retrieves the current user.
     *
     * @return the current user
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Sets the cached CareOffer object.
     *
     * @param careOffer the CareOffer object to set as cached
     */
    public void setCachedCareOffer(CareOffer careOffer) {
        this.cachedCareOffer = careOffer;
    }

    /**
     * Retrieves the cached CareOffer object.
     *
     * @return the cached CareOffer object
     */
    public CareOffer getCachedCareOffer() {
        return this.cachedCareOffer;
    }

    /**
     * Sets the cached list of CareOffer objects.
     *
     * @param careOfferList the list of CareOffer objects to set as cached
     */
    public void setCachedCareOfferList(List<CareOffer> careOfferList) {
        this.cachedCareOfferList = careOfferList;
    }

    /**
     * Retrieves the cached list of CareOffer objects.
     *
     * @return the cached list of CareOffer objects
     */
    public List<CareOffer> getCachedCareOfferList() {
        return this.cachedCareOfferList;
    }

    /**
     * Retrieves a CareOffer object from the cachedCareOfferList based on the given ID.
     *
     * @param id the ID of the CareOffer to retrieve
     * @return the CareOffer object with the given ID, or null if no CareOffer with the given ID is found
     */
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
