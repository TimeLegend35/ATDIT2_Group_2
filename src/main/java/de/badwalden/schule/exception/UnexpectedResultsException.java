package de.badwalden.schule.exception;

public class UnexpectedResultsException extends Exception {

    private final int wantedCount;
    private final int realCount;

    public UnexpectedResultsException(String message, Integer wantedCount, int realCount) {
        super(message); // Passes the message to the base Exception class
        this.wantedCount = wantedCount; // Stores the count of results that led to the exception
        this.realCount = realCount;
    }

    public int getRealCount() {
        return realCount;
    }

    public int getWantedCount() { return wantedCount; }
}