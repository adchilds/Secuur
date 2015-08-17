package com.adamchilds.secuur.exception;

/**
 *
 */
public class CannotDecodeException extends RuntimeException {

    public CannotDecodeException() {
        super("Cannot decode.");
    }

    public CannotDecodeException(String message) {
        super(message);
    }

    public CannotDecodeException(String message, Throwable throwable) {
        super(message, throwable);
    }

}