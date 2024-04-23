package ui;

import de.badwalden.schule.ui.helper.LanguageHelper;
import org.junit.jupiter.api.Test;
import java.util.Locale;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanguageHelperTest {

    @Test
    public void testSetLocaleToEnglish (){
        //assemble
        String language = "English";
        //act
        LanguageHelper.setLocale(language);
        //assert
        assertEquals(Locale.US, LanguageHelper.locale, "Locale should be set to English (US)");
    }

    @Test
    public void testGetString (){
        //assemble
        LanguageHelper.setLocale("Deutsch");
        String key = "username";
        //act
        String result = LanguageHelper.getString(key);
        //assert
        assertEquals("Benutzername", result, "Expected key message should be retrieved for German locale");
    }

}
