package de.badwalden.schule.ui.helper;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageHelper {

    static Locale locale;

    public static void setLocale(String language) {
        switch (language) {
            case "English":
                locale = new Locale("en", "US");
                break;
            case "Deutsch":
                locale = new Locale("de", "DE");
                break;
            case "Français":
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
