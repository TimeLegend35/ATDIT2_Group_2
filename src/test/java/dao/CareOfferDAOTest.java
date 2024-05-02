package dao;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import de.badwalden.schule.dao.CareOfferDAO;
import de.badwalden.schule.dao.DBConnector;
import de.badwalden.schule.ui.helper.Language;
import de.badwalden.schule.ui.helper.LanguageHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import de.badwalden.schule.exception.UnexpectedResultsException;


public class CareOfferDAOTest {
    private CareOfferDAO careOfferDAO;
    private DBConnector dbConnection;

    @BeforeEach
    public void setUp() {
        dbConnection = mock(DBConnector.class);
        careOfferDAO = new CareOfferDAO();
        LanguageHelper.setLocale(Language.ENGLISH); // Set language for exception messages etc.
    }

    @Test
    public void testGetSingleCareOffer() throws SQLException, UnexpectedResultsException {
        // Setup mock behavior for DB connection and query execution
        List<Object[]> mockResults = new ArrayList<>();
        mockResults.add(new Object[]{"data1", "data2"});  // Mimic DB returning one care offer

        when(dbConnection.executeQuery(anyString(), any())).thenReturn(mockResults);

        // Execute
        List<Object[]> results = careOfferDAO.get(1);

        // Assert
        assertNotNull(results);
        assertEquals(1, results.size(), "Should return exactly one care offer.");
    }

    @Test
    public void testGetCareOfferThrowsExceptionOnUnexpectedResults() {
        // Setup mock behavior for DB connection and query execution
        List<Object[]> mockResults = new ArrayList<>(); // No results returned

        when(dbConnection.executeQuery(anyString(), any())).thenReturn(mockResults);

        // Assert
        assertThrows(UnexpectedResultsException.class, () -> careOfferDAO.get(6),
                "Should throw UnexpectedResultsException if no results are found.");
    }

    @Test
    public void testWriteMultipleCareOffers() throws SQLException {
        List<Object[]> targets = new ArrayList<>();
        targets.add(new Object[]{101, 2, 0, "Hausaufgabenbetreuung101", "Beschreibung", 20, 1});
        targets.add(new Object[]{102, 4, 3, "Hort", "Beschreibung102", 15, 2});

        when(dbConnection.executeUpdate(anyString(), any())).thenReturn(1);  // Each update affects 1 row

        int affectedRows = careOfferDAO.write(targets);

        assertEquals(2, affectedRows, "Should report two rows affected.");
    }

    @Test
    public void testGetAllCareOffers() throws UnexpectedResultsException {
        List<Object[]> mockResults = new ArrayList<>();
        mockResults.add(new Object[]{"data1", "data2"});
        mockResults.add(new Object[]{"data3", "data4"});

        when(dbConnection.executeQuery(anyString(), any())).thenReturn(mockResults);

        List<Object[]> results = careOfferDAO.getAllCareOffers();

        assertFalse(results.isEmpty(), "Should return care offers.");
        assertEquals(5, results.size(), "Should return all care offers.");
    }

}


