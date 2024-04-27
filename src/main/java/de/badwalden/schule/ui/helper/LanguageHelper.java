package de.badwalden.schule.ui.helper;

import de.badwalden.schule.dao.DBConnector;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LanguageHelper {

    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());
    public static Locale locale;

    public static void setLocale(Language language) {
        try {
            logger.log(Level.INFO, "Locale will be set to Language: " + language);
            locale = new Locale(language.getId(), language.getCountry());
        } catch (NullPointerException e) {
            logger.log(Level.SEVERE, "Error setting locale: language is null", e);
            // Fallback to default locale
            locale = Locale.getDefault();
        }
    }

    public static String getString(String key) {
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messagebundle", locale);
            return bundle.getString(key);
        } catch (NullPointerException | MissingResourceException e) {
            logger.log(Level.SEVERE, "Error loading resource bundle or key not found", e);
            // Placeholder
            return "##" + key + "##";
        }
    }
}
