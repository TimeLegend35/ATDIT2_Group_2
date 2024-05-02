package model;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import de.badwalden.schule.dao.CareOfferDAO;
import de.badwalden.schule.model.CareOffer;
import de.badwalden.schule.model.Supervisor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CareOfferTest {

    private CareOffer careOffer;
    private CareOfferDAO mockCareOfferDao;
    private Supervisor supervisor;

    @BeforeEach
    public void setUp() {
        // Initialize mocks
        mockCareOfferDao = mock(CareOfferDAO.class);
        supervisor = mock(Supervisor.class);
        when(supervisor.getId()).thenReturn(101);  // Mock supervisor ID

        // Inject mock DAO into static field if necessary
        // You might need to adjust the design to allow injecting mock, or use reflection if necessary.
        CareOffer.careOfferDao = mockCareOfferDao;

        // Create an instance of CareOffer
        careOffer = new CareOffer(1, supervisor, 3, 1, "Homework Help", "Assist with daily homework.", 30);
    }

    @Test
    public void testUpdateSuccess() {
        // Setup
        when(mockCareOfferDao.write(any(List.class))).thenReturn(1);

        // Action
        careOffer.update();

        // Assert
        verify(mockCareOfferDao, times(1)).write(any(List.class));  // Ensure DAO's write method is called
        // Additionally, check logs if necessary (this might require a more complex setup)
    }

    @Test
    public void testToObjectArray() {
        Object[] expected = {101, 3, 1, "Homework Help", "Assist with daily homework.", 30, 1};
        Object[] actual = careOffer.toObjectArray();

        // Assert
        assertArrayEquals(expected, actual, "The object array should match the CareOffer attributes.");
    }
}

