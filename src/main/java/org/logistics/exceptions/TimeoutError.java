package org.logistics.exceptions;

public class TimeoutError extends RuntimeException {
    public TimeoutError(String message) {
        super(message);
    }
}
