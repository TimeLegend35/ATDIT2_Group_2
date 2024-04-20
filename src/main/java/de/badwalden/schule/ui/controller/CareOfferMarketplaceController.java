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

public class CareOfferMarketplaceController implements DataController {

    public CareOfferMarketplaceController() {}

    public void showObjectPage(CareOffer offer) {
        // Save the offer in the session
        Session.getInstance().setCachedCareOffer(offer);
        // Navigate into the careOffer Object Page
        Main.navigationHelper.setContentView(offer);
    }

    @Override
    public Object[] getData() {

        List<Object[]> results = CareOfferDAO.getAllCareOffers();

        List<Service> careOffers = new ArrayList<>();
        Service newCareOffer;

        for (Object[] row : results) {

            int id = (int) row[0];
            int supervisorId = (int) row[1];
            Supervisor supervisor = new Supervisor(supervisorId, "MasterSupervisor") ;
            int oldestClassLevel = (int) row[2];
            int youngestClassLevel = (int) row[3];
            String careOfferName = (String) row[4];
            String description = (String) row[5];
            int seatsAvailable = (int) row[6];

            newCareOffer = new CareOffer(id, supervisor, oldestClassLevel, youngestClassLevel, careOfferName, description, seatsAvailable);
            careOffers.add(newCareOffer);
        }

        return careOffers.toArray();
    }
}

