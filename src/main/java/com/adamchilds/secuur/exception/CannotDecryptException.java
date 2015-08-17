package com.adamchilds.secuur.exception;

/**
 *
 */
public class CannotDecryptException extends RuntimeException {

    public CannotDecryptException() {
        super("Cannot decrypt.");
    }

    public CannotDecryptException(String message) {
        super(message);
    }

    public CannotDecryptException(String message, Throwable throwable) {
        super(message, throwable);
    }

}