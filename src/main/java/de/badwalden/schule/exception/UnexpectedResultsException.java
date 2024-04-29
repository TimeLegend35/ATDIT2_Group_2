package de.badwalden.schule.exception;

public class UnexpectedResultsException extends Exception {

    private final int resultCount;

    public UnexpectedResultsException(String message, int resultCount) {
        super(message); // Passes the message to the base Exception class
        this.resultCount = resultCount; // Stores the count of results that led to the exception
    }

    // Getter for the result count
    public int getResultCount() {
        return resultCount;
    }
}