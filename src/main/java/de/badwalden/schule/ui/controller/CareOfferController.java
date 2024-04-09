package de.badwalden.schule.ui.controller;

import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.ui.views.CareOfferMarketplaceView;
import de.badwalden.schule.ui.views.CareOfferView;
import de.badwalden.schule.ui.views.MainView;

public class CareOfferController {
    private MainView mainView;
    public CareOfferController(MainView mainView, CareOfferView careOfferView) {

    }

    public void navigateToCareOfferMarketplace(String id, MainView mainView) {
        // Implementation depends on how we are handling navigation.
        // This could update a main view to show the care offer detail view for the given id.
        System.out.println("Navigating to details of care offer with ID: " + id);
        // Test navigation to Object Pages.
        mainView.setContentView(new CareOfferMarketplaceView(mainView));
    }
}
