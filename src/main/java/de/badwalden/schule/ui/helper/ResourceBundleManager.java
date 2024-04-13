package de.badwalden.schule.ui.helper;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundleManager {
    private static final String BUNDLE_BASE_NAME = "MessagesBundle";

    public ResourceBundle getResourceBundle(Locale locale) {
        return ResourceBundle.getBundle(BUNDLE_BASE_NAME, locale);
    }
}
