package ui;

import de.badwalden.schule.Main;
import de.badwalden.schule.model.Parent;
import de.badwalden.schule.model.outOfScope.Admin;
import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.model.Supervisor;
import de.badwalden.schule.ui.controller.CareOfferMarketplaceController;
import de.badwalden.schule.model.helper.Session;
import de.badwalden.schule.ui.helper.Language;
import de.badwalden.schule.ui.helper.LanguageHelper;
import de.badwalden.schule.ui.views.CareOfferMarketplaceView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CareOfferMarketplaceView class.
 */

public class CareOfferMarketplaceTest {

    private CareOfferMarketplaceView marketplaceView;
    private CareOffer sampleCareOffer;

    @BeforeAll
    public static void initialSetUp() {
        Setup.start_up_javaFX_plattform();

    }

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    public void setUp() {
        LanguageHelper.setLocale(Language.GERMAN);
        Parent parent = new Parent(500, "String firstName", "String lastName", "String cityOfResidence");
        Session sessionMock = Session.getInstance();
        sessionMock.setCurrentUser(parent);
        Supervisor supervisor = new Supervisor(1, "Alice Bauer");
        sampleCareOffer = new CareOffer(1, supervisor, 4, 1, "Test Care Offer", "Test description", 10);
        List<CareOffer> careOfferList = new ArrayList<>();
        careOfferList.add(sampleCareOffer);
        careOfferList.add(sampleCareOffer);
        sessionMock.setCachedCareOfferList(careOfferList);
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
        assertInstanceOf(VBox.class, marketplaceView.getContent());
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
        assertFalse(contentBox.getChildren().isEmpty());
    }

}

