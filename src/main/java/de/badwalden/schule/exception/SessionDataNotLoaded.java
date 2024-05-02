package de.badwalden.schule.exception;

/**
 * Exception thrown when necessary session data has not been loaded.
 */
public class SessionDataNotLoaded extends Exception {
    private final String dataPart;

    /**
     * Constructs a new exception with the specified detail message and the part of data missing.
     *
     * @param message the detail message
     * @param dataPart the part of the session data that failed to load
     */
    public SessionDataNotLoaded(String message, String dataPart) {
        super(message);

        this.dataPart = dataPart;
    }

    public String getDataPart() { return this.dataPart; }
}
