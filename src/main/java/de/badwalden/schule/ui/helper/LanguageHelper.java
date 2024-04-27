package de.badwalden.schule.ui.helper;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageHelper {

    public static Locale locale;

    public static void setLocale(Language language) {
        System.out.println("Language: " + language.toString());
        switch (language) {
            case GERMAN:
                locale = new Locale("de", "DE");
                break;
            case ENGLISH:
                locale = new Locale("en", "US");
                break;
            case FRENCH:
                locale = new Locale("fr", "FR");
                break;
            default:
                locale = new Locale("en", "US");
        }
    }

    public static String getString(String key) {
        // Assuming your resource bundle base name is 'Messages'
        ResourceBundle bundle = ResourceBundle.getBundle("Messagebundle", locale);
        return bundle.getString(key);
    }
}
