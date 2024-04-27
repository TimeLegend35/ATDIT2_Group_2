package de.badwalden.schule.ui.helper;

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

    public String getId() {
        return id;
    }

    public String getCountry() { return country; }

    public static Language getLanguage(String id) {
        for(Language language : values()) {
            if(language.getId().equals(id)) {
                return language;
            }
        };
        return Language.GERMAN; // Could also throw an exception todo
    }
}
