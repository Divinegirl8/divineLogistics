package org.logistics.exceptions;

public class PasswordDoesNotExist extends RuntimeException {
    public PasswordDoesNotExist(String message) {
        super(message);
    }
}
