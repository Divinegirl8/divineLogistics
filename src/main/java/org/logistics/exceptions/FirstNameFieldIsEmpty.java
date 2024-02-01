package org.logistics.exceptions;

public class FirstNameFieldIsEmpty extends RuntimeException {
    public FirstNameFieldIsEmpty(String message) {
        super(message);
    }
}
