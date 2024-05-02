package dao;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import de.badwalden.schule.dao.DBConnector;
import de.badwalden.schule.ui.helper.Language;
import de.badwalden.schule.ui.helper.LanguageHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.sql.*;
import java.util.List;


public class DBConnectorTest {
    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockStatement;

    @Mock
    private ResultSet mockResultSet;

    @BeforeEach
    public void setUp() {
        LanguageHelper.setLocale(Language.ENGLISH);

        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
    }

    @Test
    public void testSingletonInstance() {
        DBConnector firstInstance = DBConnector.getInstance();
        DBConnector secondInstance = DBConnector.getInstance();
        assertSame(firstInstance, secondInstance, "Both instances should be the same.");
    }

    @Test
    public void testConnectionEstablishment() throws SQLException {
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        assertDoesNotThrow(() -> DBConnector.getInstance().connect(), "Connection should be established without throwing exceptions.");
    }

}

