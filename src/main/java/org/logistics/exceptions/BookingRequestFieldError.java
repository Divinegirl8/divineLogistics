package org.logistics.exceptions;

public class BookingRequestFieldError extends RuntimeException {
    public BookingRequestFieldError(String message) {
        super(message);
    }
}
