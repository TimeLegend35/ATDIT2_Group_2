package de.badwalden.schule.ui.helper;

import java.util.NoSuchElementException;

public enum Language {
    GERMAN("\uD83C\uDDE9\uD83C\uDDEA | Deutsch", "de", "DE"),
    ENGLISH("\uD83C\uDDFA\uD83C\uDDF8 | English", "en", "US"),
    FRENCH("\uD83C\uDDEB\uD83C\uDDF7 | Français", "fr", "FR");

    private final String displayName;
    private final String id;
    private final String country;

    Language(String displayName, String id, String country) {
        this.displayName = displayName;
        this.id = id;
        this.country = country;
    }

    @Override
    public String toString() {
        return displayName;
    }

    /**
     * Retrieves the ID associated with the Language.
     *
     * @return the ID of the Language
     */
    public String getId() {
        return id;
    }

    /**
     * Retrieves the country code associated with the Language.
     *
     * @return the country code of the Language
     */
    public String getCountry() {
        return country;
    }

    /**
     * Get the Language enum based on the given ID.
     *
     * @param id the ID to search for
     * @return the Language enum matching the ID
     * @throws NoSuchElementException if the Language with the provided ID is not found
     */
    public static Language getLanguage(String id) throws NoSuchElementException {
        for (Language language : values()) {
            if (language.getId().equals(id)) {
                return language;
            }
        }
        ;
        throw new NoSuchElementException("Language with ID '" + id + "' not found");
    }
}
