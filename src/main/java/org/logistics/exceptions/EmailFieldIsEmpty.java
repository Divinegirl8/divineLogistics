package org.logistics.exceptions;

public class EmailFieldIsEmpty extends RuntimeException {
    public EmailFieldIsEmpty(String message) {
        super(message);
    }
}
