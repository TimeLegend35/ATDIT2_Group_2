package de.badwalden.schule.ui.controller;

import de.badwalden.schule.Main;
import de.badwalden.schule.dao.CareOfferDAO;
import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.model.Service;
import de.badwalden.schule.model.Supervisor;
import de.badwalden.schule.ui.helper.Session;
import de.badwalden.schule.ui.views.CareOfferMarketplaceView;
import de.badwalden.schule.ui.views.CareOfferView;
import de.badwalden.schule.ui.views.DataController;
import de.badwalden.schule.ui.views.MainView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class CareOfferMarketplaceController {

    public CareOfferMarketplaceController() {}

    public void showObjectPage(CareOffer offer) {
        // Save the offer in the session
        Session.getInstance().setCachedCareOffer(offer);
        // Navigate into the careOffer Object Page
        Main.navigationHelper.setContentView(offer);
    }
}

