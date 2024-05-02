package ui;

import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.model.Parent;
import de.badwalden.schule.model.Supervisor;
import de.badwalden.schule.ui.controller.CareOfferController;
import de.badwalden.schule.model.helper.Session;
import de.badwalden.schule.ui.helper.Language;
import de.badwalden.schule.ui.helper.LanguageHelper;
import de.badwalden.schule.ui.views.CareOfferView;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CareOfferController class.
 */
public class CareOfferViewTest {

public CareOffer sampleCareOffer;
    private CareOfferController careOfferController;

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
        sessionMock.setCachedCareOffer(sampleCareOffer);



        CareOfferView careOfferViewMock = new CareOfferView();
        careOfferController = new CareOfferController(careOfferViewMock);
    }

    /**
     * Tests the getData() method of the CareOfferController class.
     * Checks if the method returns the expected data.
     */
    @Test
    public void testGetData() {
        Object[] data = careOfferController.getData();
        // Assert
        assertNotNull(data);
        assertEquals(1, data.length);
        assertInstanceOf(CareOffer.class, data[0]);
        assertEquals(sampleCareOffer, data[0]);
    }
}

