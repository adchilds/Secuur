package com.adamchilds.secuur.exception;

/**
 *
 */
public class CannotEncryptException extends RuntimeException {

    public CannotEncryptException() {
        super("Cannot encrypt.");
    }

    public CannotEncryptException(String message) {
        super(message);
    }

    public CannotEncryptException(String message, Throwable throwable) {
        super(message, throwable);
    }

}