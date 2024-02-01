package org.logistics.exceptions;

public class PasswordFieldIsEmpty extends RuntimeException {
    public PasswordFieldIsEmpty(String message) {
        super(message);
    }
}
