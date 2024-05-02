package ui;

import de.badwalden.schule.ui.helper.Language;
import de.badwalden.schule.ui.helper.LanguageHelper;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the LanguageHelper class.
 */
public class LanguageHelperTest {

    @Test
    public void testSetLocale() {
        LanguageHelper.setLocale(Language.GERMAN);
        assertEquals(new Locale("de", "DE"), LanguageHelper.locale);
    }

    @Test
    public void testGetString() {
        LanguageHelper.setLocale(Language.ENGLISH);
        assertEquals("Username", LanguageHelper.getString("login_username"));
        LanguageHelper.setLocale(Language.GERMAN);
        assertEquals("Benutzername", LanguageHelper.getString("login_username"));
    }


    @Test
    public void testGetLanguageFound() {
        Language language = Language.getLanguage("en");
        assertEquals(Language.ENGLISH, language);
    }

    @Test
    public void testGetLanguageNotFound() {
        assertThrows(NoSuchElementException.class, () -> {
            Language.getLanguage("es");
        });
    }


}

