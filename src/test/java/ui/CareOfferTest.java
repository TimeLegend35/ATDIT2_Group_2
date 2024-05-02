package ui;

import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.model.Supervisor;
import de.badwalden.schule.ui.controller.CareOfferController;
import de.badwalden.schule.model.helper.Session;
import de.badwalden.schule.ui.views.CareOfferView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the CareOfferController class.
 */
public class CareOfferTest {

    private CareOfferView careOfferViewMock;
    private CareOfferController careOfferController;
    private Session sessionMock;

    /**
     * Sets up the test environment before each test method.
     */
    @BeforeEach
    public void setUp() {
        careOfferViewMock = new CareOfferView();
        sessionMock = new Session();
        careOfferController = new CareOfferController(careOfferViewMock);
    }

    /**
     * Tests the getData() method of the CareOfferController class.
     * Checks if the method returns the expected data.
     */
    @Test
    public void testGetData() {
        // Assemble
        Supervisor supervisor = new Supervisor(1, "Alice Bauer");
        CareOffer expectedCareOffer = new CareOffer(1, supervisor, 4, 1, "Test Care Offer", "Test description", 10);
        sessionMock.setCachedCareOffer(expectedCareOffer);
        // Act
        Object[] data = careOfferController.getData();
        // Assert
        assertNotNull(data);
        assertTrue(data.length == 1);
        assertTrue(data[0] instanceof CareOffer);
        assertEquals(expectedCareOffer, data[0]);
    }
}

