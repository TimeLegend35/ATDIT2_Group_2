package de.badwalden.schule.exception;


/**
 * Exception thrown when the number of results from a database query does not meet the expected count.
 */
public class UnexpectedResultsException extends Exception {

    private final int wantedCount;
    private final int realCount;

    /**
     * Constructs a new exception with the specified detail message, expected count, and actual count.
     *
     * @param message the detail message
     * @param wantedCount the expected number of results
     * @param realCount the actual number of results obtained
     */
    public UnexpectedResultsException(String message, int wantedCount, int realCount) {

        super(message);
        this.wantedCount = wantedCount;
        this.realCount = realCount;
    }

    public int getRealCount() {
        return realCount;
    }

    public int getWantedCount() {
        return wantedCount;
    }

}