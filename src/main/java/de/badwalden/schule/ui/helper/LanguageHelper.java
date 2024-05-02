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

    /**
     * Sets the locale based on the provided Language object.
     *
     * @param language the Language object containing the locale information
     */
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

    /**
     * Retrieves a localized string for the given key.
     *
     * @param key the key for the desired localized string
     * @return the localized string for the given key, or a placeholder string if the resource bundle or key is not found
     */
    public static String getString(String key) {
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messagebundle", locale);
            return bundle.getString(key);
        } catch (NullPointerException | MissingResourceException e) {
            logger.log(Level.SEVERE, "Key not found: " + key, e);
            return "Key not found";  // Return a default message or handle the error appropriately
        }
    }
}
