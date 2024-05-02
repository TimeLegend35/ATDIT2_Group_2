package de.badwalden.schule.ui.controller;

import de.badwalden.schule.Main;
import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.model.helper.Session;

public class CareOfferMarketplaceController implements DataController {

    public CareOfferMarketplaceController() {}

    /**
     * Saves the given CareOffer in the session and navigates into the careOffer Object Page.
     *
     * @param  offer  the CareOffer to be saved and displayed
     */
    public void showObjectPage(CareOffer offer) {
        // Save the offer in the session
        Session.getInstance().setCachedCareOffer(offer);
        // Navigate into the careOffer Object Page
        Main.navigationHelper.setContentView(offer);
    }

    @Override
    public Object[] getData() {
        return Session.getInstance().getCachedCareOfferList().toArray();
    }
}

