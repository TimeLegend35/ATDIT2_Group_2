package de.badwalden.schule.ui.controller;

import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.ui.views.CareOfferMarketplaceView;
import de.badwalden.schule.ui.views.CareOfferView;
import de.badwalden.schule.ui.views.MainView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CareOfferMarketplaceController {
    private final MainView mainview;
    private ObservableList<CareOffer> careOffers = FXCollections.observableArrayList();

    public CareOfferMarketplaceController(MainView mainview) {
        this.mainview = mainview;

        //Example Care Offer (mocked)
        CareOffer careOffer1 = new CareOffer();
        careOffer1.setId(1);
        careOffer1.setName("Sample Service");
        careOffer1.setDescription("This is a sample service description.");
        careOffer1.setNumberOfSeats(12);
        //Example Care Offer (mocked)
        CareOffer careOffer = new CareOffer();
        careOffer.setId(2);
        careOffer.setName("Another something Service");
        careOffer.setDescription("This is an example for a care offer description.");

        careOffers.add(careOffer1);
        careOffers.add(careOffer);
    }

    public ObservableList<CareOffer> getCareOffers() {
        return careOffers;
    }

    public void navigateToCareOffer(String id, CareOffer offer) {
        // Implementation depends on how we are handling navigation.
        // This could update a main view to show the care offer detail view for the given id.
        System.out.println("Navigating to details of care offer with ID: " + id);
        // Test navigation to Object Pages.
        mainview.setContentView(new CareOfferView(offer));
    }

}

