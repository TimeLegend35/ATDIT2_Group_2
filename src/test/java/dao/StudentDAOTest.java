package dao;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import de.badwalden.schule.dao.DBConnector;
import de.badwalden.schule.dao.StudentDAO;
import de.badwalden.schule.ui.helper.Language;
import de.badwalden.schule.ui.helper.LanguageHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import de.badwalden.schule.exception.UnexpectedResultsException;

public class StudentDAOTest {
    private StudentDAO studentDAO;
    private DBConnector dbConnection;

    @BeforeEach
    public void setUp() {
        dbConnection = mock(DBConnector.class);
        studentDAO = new StudentDAO();
        LanguageHelper.setLocale(Language.ENGLISH);
    }

    @Test
    public void testGetSingleStudent() throws UnexpectedResultsException {
        List<Object[]> mockResults = new ArrayList<>();
        mockResults.add(new Object[]{"John", "Doe", 10}); // Mimic correct result set

        when(dbConnection.executeQuery(anyString(), any())).thenReturn(mockResults);

        List<Object[]> result = studentDAO.get(1);
        assertEquals(1, result.size(), "Should return exactly one student.");
    }

    @Test
    public void testUpdateStudents() {
        List<Object[]> targets = new ArrayList<>();
        targets.add(new Object[]{1, 2, "Emma", "Smith", 9, true, true});
        targets.add(new Object[]{2, 3, "Liam", "Jones", 8, true, true});

        when(dbConnection.executeUpdate(anyString(), any())).thenReturn(1);

        int result = studentDAO.write(targets);
        assertEquals(2, result, "Should return the total rows affected.");
    }

    @Test
    public void testGetStudentsIdFromParent() {
        List<Object[]> mockResults = new ArrayList<>();
        mockResults.add(new Object[]{1});
        mockResults.add(new Object[]{2});

        when(dbConnection.executeQuery(anyString(), any())).thenReturn(mockResults);

        List<Object[]> result = studentDAO.getStudentsIdFromParent(1);
        assertEquals(3, result.size(), "Should return list of student IDs.");
    }

    @Test
    public void testAddStudentToCareOffer() {
        when(dbConnection.executeUpdate(anyString(), any())).thenReturn(1);

        assertDoesNotThrow(() -> studentDAO.addStudentToCareOffer(1, 5),
                "Should add student to care offer without throwing exceptions.");
    }

    @Test
    public void testRemoveStudentFromCareOffer() {
        when(dbConnection.executeUpdate(anyString(), any())).thenReturn(1);

        assertDoesNotThrow(() -> studentDAO.removeStudentFromCareOffer(1, 5),
                "Should remove student from care offer without throwing exceptions.");
    }

}

