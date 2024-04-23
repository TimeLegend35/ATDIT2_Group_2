package ui;

import de.badwalden.schule.Main;
import de.badwalden.schule.model.Admin;
import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.model.Supervisor;
import de.badwalden.schule.ui.controller.CareOfferMarketplaceController;
import de.badwalden.schule.ui.helper.Session;
import de.badwalden.schule.ui.views.CareOfferMarketplaceView;
import javafx.application.Platform;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CareOfferMarketplaceTest {

    private CareOfferMarketplaceView marketplaceView;

    @BeforeEach
    public void setUp() {
        Session.getInstance().setCurrentUser(new Admin());
        marketplaceView = new CareOfferMarketplaceView();
    }

    @Test
    public void testMarketplaceUIComponents() {
            //assemble
            //act
            //assert
            assertTrue(marketplaceView.getContent() instanceof VBox);
    }

    @Test
    public void testMarketplaceContent() {
            //assemble
            //act
            //assert
            VBox contentBox = (VBox) marketplaceView.getContent();
            assertTrue(contentBox.getChildren().size() > 0);
    }

    @Test
    public void testShowObjectPage() {
            //assemble
            CareOfferMarketplaceController controller = new CareOfferMarketplaceController();
            CareOffer offer = new CareOffer(1, new Supervisor(1, "Bob Bauer"), 4, 1, "Test Offer", "Test Description", 5);
            //act
            controller.showObjectPage(offer);
            //assert
            assertEquals(offer, Session.getInstance().getCachedCareOffer());
    }
}
