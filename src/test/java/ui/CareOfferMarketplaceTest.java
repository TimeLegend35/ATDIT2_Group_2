package ui;

import de.badwalden.schule.model.outOfScope.Admin;
import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.model.Supervisor;
import de.badwalden.schule.ui.controller.CareOfferMarketplaceController;
import de.badwalden.schule.model.helper.Session;
import de.badwalden.schule.ui.views.CareOfferMarketplaceView;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CareOfferMarketplaceView class.
 */
public class CareOfferMarketplaceTest {

    private CareOfferMarketplaceView marketplaceView;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    public void setUp() {
        Session.getInstance().setCurrentUser(new Admin());
        marketplaceView = new CareOfferMarketplaceView();
    }

    /**
     * Tests the UI components of the marketplace view.
     * Checks if the content of the marketplace view is an instance of VBox.
     */
    @Test
    public void testMarketplaceUIComponents() {
        // Assemble
        // Act
        // Assert
        assertTrue(marketplaceView.getContent() instanceof VBox);
    }

    /**
     * Tests the content of the marketplace view.
     * Checks if the content box of the marketplace view contains child elements.
     */
    @Test
    public void testMarketplaceContent() {
        // Assemble
        // Act
        // Assert
        VBox contentBox = (VBox) marketplaceView.getContent();
        assertTrue(contentBox.getChildren().size() > 0);
    }

    /**
     * Tests the method to show the object page in the marketplace controller.
     * Checks if the correct care offer is cached in the session after showing the object page.
     */
    @Test
    public void testShowObjectPage() {
        // Assemble
        CareOfferMarketplaceController controller = new CareOfferMarketplaceController();
        CareOffer offer = new CareOffer(1, new Supervisor(1, "Bob Bauer"), 4, 1, "Test Offer", "Test Description", 5);
        // Act
        controller.showObjectPage(offer);
        // Assert
        assertEquals(offer, Session.getInstance().getCachedCareOffer());
    }
}

