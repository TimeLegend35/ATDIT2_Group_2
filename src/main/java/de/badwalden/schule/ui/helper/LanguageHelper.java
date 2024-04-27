package de.badwalden.schule.ui.helper;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageHelper {

    public static Locale locale;

    public static void setLocale(Language language) {
        System.out.println("Language: " + language.toString());
        locale = new Locale(language.getId(), language.getCountry());
    }

    public static String getString(String key) {
        // Assuming your resource bundle base name is 'Messages'
        ResourceBundle bundle = ResourceBundle.getBundle("Messagebundle", locale);
        return bundle.getString(key);
    }
}
