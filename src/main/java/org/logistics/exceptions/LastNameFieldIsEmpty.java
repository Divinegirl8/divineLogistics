package org.logistics.exceptions;

public class LastNameFieldIsEmpty extends RuntimeException {
    public LastNameFieldIsEmpty(String message) {
        super(message);
    }
}
