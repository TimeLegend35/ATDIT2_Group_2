package de.badwalden.schule.exception;

public class SessionDataNotLoaded extends Exception {
    private final String dataPart;

    public SessionDataNotLoaded(String message, String dataPart) {
        super(message);

        this.dataPart = dataPart;
    }

    public String getDataPart() { return this.dataPart; }
}
