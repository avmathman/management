package com.invent.management.domain.exception;

/**
 * Project specific exception.
 */
public class ManagementException extends RuntimeException {

    /**
     * Initializes a new {@link ManagementException} instance.
     *
     * @param message - error description message.
     */
    public ManagementException(String message) {
        super(message);
    }

    /**
     * Initializes a new {@link ManagementException} instance with the specified
     * description message and cause.
     *
     * @param message - error description message.
     * @param cause - the original cause of the exception.
     */
    public ManagementException(String message, Throwable cause) {
        super(message, cause);
    }
}
