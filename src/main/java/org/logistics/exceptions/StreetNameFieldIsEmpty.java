package org.logistics.exceptions;

public class StreetNameFieldIsEmpty extends RuntimeException {
    public StreetNameFieldIsEmpty(String message) {
        super(message);
    }
}
