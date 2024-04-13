package de.badwalden.schule.ui.controller;

import de.badwalden.schule.Main;
import de.badwalden.schule.dao.CareOfferDAO;
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
        careOffers = CareOfferDAO.getAll();
    }

    public void showObjectPage(CareOffer offer) {
        Main.navigationHelper.setContentView(offer);
    }

    public ObservableList<CareOffer> getCareOffers() {
        return careOffers;
    }

}

