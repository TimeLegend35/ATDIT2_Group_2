package de.badwalden.schule.ui.helper;

public enum Language {
    GERMAN("\uD83C\uDDE9\uD83C\uDDEA | Deutsch", "de"),
    ENGLISH("\uD83C\uDDFA\uD83C\uDDF8 | English", "en"),
    FRENCH("\uD83C\uDDEB\uD83C\uDDF7 | Français", "fr");

    private final String displayName;
    private final String id;

    Language(String displayName, String id) {
        this.displayName = displayName;
        this.id = id;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public String getId() {
        return id;
    }

    public static Language getLanguage(String id) {
        for(Language language : values()) {
            if(language.getId().equals(id)) {
                return language;
            }
        };
        return Language.GERMAN; // Could also throw an exception todo
    }
}
