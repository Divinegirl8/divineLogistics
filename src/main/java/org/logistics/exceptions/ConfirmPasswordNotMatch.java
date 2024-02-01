package org.logistics.exceptions;

public class ConfirmPasswordNotMatch extends RuntimeException {
    public ConfirmPasswordNotMatch(String message) {
        super(message);
    }
}
