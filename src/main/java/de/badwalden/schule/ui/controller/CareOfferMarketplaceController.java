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
        CareOffer careOffer2 = new CareOffer();
        careOffer2.setId(3);
        careOffer2.setName("Yet Another Service");
        careOffer2.setDescription("This is another example service description.");
        careOffer2.setNumberOfSeats(8);

        //Example Care Offer (mocked)
        CareOffer careOffer3 = new CareOffer();
        careOffer3.setId(4);
        careOffer3.setName("Service XYZ");
        careOffer3.setDescription("Description for Service XYZ.");
        careOffer3.setNumberOfSeats(10);

        //Example Care Offer (mocked)
        CareOffer careOffer4 = new CareOffer();
        careOffer4.setId(5);
        careOffer4.setName("Service ABC");
        careOffer4.setDescription("Description for Service ABC.");
        careOffer4.setNumberOfSeats(15);

        //Example Care Offer (mocked)
        CareOffer careOffer5 = new CareOffer();
        careOffer5.setId(6);
        careOffer5.setName("Service 123");
        careOffer5.setDescription("Description for Service 123.");
        careOffer5.setNumberOfSeats(20);

        //Example Care Offer (mocked)
        CareOffer careOffer6 = new CareOffer();
        careOffer6.setId(7);
        careOffer6.setName("Service Test");
        careOffer6.setDescription("Description for Service Test.");
        careOffer6.setNumberOfSeats(18);

        //Example Care Offer (mocked)
        CareOffer careOffer7 = new CareOffer();
        careOffer7.setId(8);
        careOffer7.setName("Service Example");
        careOffer7.setDescription("Description for Service Example.");
        careOffer7.setNumberOfSeats(25);

        //Example Care Offer (mocked)
        CareOffer careOffer8 = new CareOffer();
        careOffer8.setId(9);
        careOffer8.setName("Service Mock");
        careOffer8.setDescription("Description for Service Mock.");
        careOffer8.setNumberOfSeats(30);

        //Example Care Offer (mocked)
        CareOffer careOffer9 = new CareOffer();
        careOffer9.setId(10);
        careOffer9.setName("Service Trial");
        careOffer9.setDescription("Description for Service Trial.");
        careOffer9.setNumberOfSeats(22);

        //Example Care Offer (mocked)
        CareOffer careOffer10 = new CareOffer();
        careOffer10.setId(11);
        careOffer10.setName("Service Demo");
        careOffer10.setDescription("Description for Service Demo.");
        careOffer10.setNumberOfSeats(14);

        //Example Care Offer (mocked)
        CareOffer careOffer11 = new CareOffer();
        careOffer11.setId(12);
        careOffer11.setName("Service Showcase");
        careOffer11.setDescription("Description for Service Showcase.");
        careOffer11.setNumberOfSeats(17);

        careOffers.add(careOffer1);
        careOffers.add(careOffer2);
        careOffers.add(careOffer3);
        careOffers.add(careOffer4);
        careOffers.add(careOffer5);
        careOffers.add(careOffer6);
        careOffers.add(careOffer7);
        careOffers.add(careOffer8);
        careOffers.add(careOffer9);
        careOffers.add(careOffer10);
        careOffers.add(careOffer11);
    }

    public void showObjectPage(CareOffer offer) {
        Main.navigationHelper.setContentViewToObjectPage(offer);
    }

    public ObservableList<CareOffer> getCareOffers() {
        return careOffers;
    }

}

