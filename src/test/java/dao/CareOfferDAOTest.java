package dao;

import static org.testng.AssertJUnit.*;
import de.badwalden.schule.dao.CareOfferDAO;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class CareOfferDAOTest {

    // test
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        // Perform setup tasks before running tests, like setting up a test database
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        // Perform cleanup tasks after running tests, like closing connections
    }

    @Test
    public void testGet() {
        // Test the get method with a valid care offer ID
        List<Object[]> results = CareOfferDAO.get(1);
        assertNotNull(results);
        assertEquals(1, results.size());
    }

    @Test
    public void testGetCareOffersForStudent() {
        // Test the getCareOffersForStudent method with a valid student ID
        List<Object[]> results = CareOfferDAO.getCareOffersForStudent(1);
        assertNotNull(results);
    }

    @Test
    public void testGetAllCareOffers() {
        // Test the getAllCareOffers method
        List<Object[]> results = CareOfferDAO.getAllCareOffers();
        assertNotNull(results);
        assertTrue(results.size() > 0);
    }

    @Test
    public void testRemoveChildFromCareOffer() {
        // Test the removeChildFromCareOffer method
        // You may need to mock the database connection for this test
        // Assert that the method doesn't throw any exceptions
        CareOfferDAO.removeChildFromCareOffer(1, 1);
    }

    @Test
    public void testAddChildToCareOffer() {
        // Test the addChildtoCareoffer method
        // You may need to mock the database connection for this test
        // Assert that the method doesn't throw any exceptions
        CareOfferDAO.addChildtoCareoffer(1, 1);
    }
}

