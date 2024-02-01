package org.logistics.exceptions;

public class ConfirmPasswordFieldIsEmpty extends RuntimeException {
    public ConfirmPasswordFieldIsEmpty(String message) {
        super(message);
    }
}
