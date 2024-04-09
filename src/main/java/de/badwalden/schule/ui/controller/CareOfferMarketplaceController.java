package de.badwalden.schule.ui.controller;

import de.badwalden.schule.Main;
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
    private ObservableList<CareOffer> careOffers = FXCollections.observableArrayList();

    public CareOfferMarketplaceController() {

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

    public void showObjectPage(CareOffer offer) {
        Main.navigationHelper.setContentViewToObjectPage(offer);
    }

    public ObservableList<CareOffer> getCareOffers() {
        return careOffers;
    }

}

