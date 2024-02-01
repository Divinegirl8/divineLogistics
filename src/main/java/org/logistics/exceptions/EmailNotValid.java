package org.logistics.exceptions;

public class EmailNotValid extends RuntimeException {
    public EmailNotValid(String message) {
        super(message);
    }
}
