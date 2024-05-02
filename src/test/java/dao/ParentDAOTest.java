package dao;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import de.badwalden.schule.dao.DBConnector;
import de.badwalden.schule.dao.ParentDAO;
import de.badwalden.schule.ui.helper.Language;
import de.badwalden.schule.ui.helper.LanguageHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import de.badwalden.schule.exception.UnexpectedResultsException;

public class ParentDAOTest {
    private ParentDAO parentDAO;
    private DBConnector dbConnection;

    @BeforeEach
    public void setUp() {
        dbConnection = mock(DBConnector.class);
        parentDAO = new ParentDAO();
        LanguageHelper.setLocale(Language.ENGLISH); // Set locale for exception messages
    }

    @Test
    public void testGetSingleParent() throws SQLException, UnexpectedResultsException {
        // Setup mock behavior for DB connection and query execution
        List<Object[]> mockResults = new ArrayList<>();
        mockResults.add(new Object[]{15, "Mannfred", "Schulz", "Bad Walden"});

        when(dbConnection.executeQuery(anyString(), any())).thenReturn(mockResults);

        // Execute
        List<Object[]> results = parentDAO.get(12);

        // Assert
        assertNotNull(results);
        assertEquals(1, results.size(), "Should return exactly one parent.");
    }

    @Test
    public void testGetParentThrowsExceptionOnUnexpectedResults() {
        // Setup mock behavior for DB connection and query execution
        List<Object[]> mockResults = new ArrayList<>(); // No results returned

        when(dbConnection.executeQuery(anyString(), any())).thenReturn(mockResults);

        // Assert
        Exception exception = assertThrows(UnexpectedResultsException.class, () -> parentDAO.get(100),
                "Should throw UnexpectedResultsException if no results are found.");
        // assertTrue(exception.getMessage().contains("expected: 1, got: 0"));
    }

}

