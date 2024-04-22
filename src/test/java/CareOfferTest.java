import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.ui.controller.CareOfferController;
import de.badwalden.schule.ui.helper.Session;
import de.badwalden.schule.ui.views.CareOfferView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CareOfferTest {

    private CareOfferView careOfferViewMock;
    private CareOfferController careOfferController;
    private Session sessionMock;

    @BeforeEach
    public void setUp() {
        careOfferViewMock = new CareOfferView();
        sessionMock = new Session();
        careOfferController = new CareOfferController(careOfferViewMock);
    }

    @Test
    public void testGetData() {
        //assemble
        CareOffer expectedCareOffer = new CareOffer();
        sessionMock.setCachedCareOffer(expectedCareOffer);
        //act
        Object[] data = careOfferController.getData();
        //assert
        assertNotNull(data);
        assertTrue(data.length == 1);
        assertTrue(data[0] instanceof CareOffer);
        assertEquals(expectedCareOffer, data[0]);
    }
}
