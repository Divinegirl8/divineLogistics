package org.logistics.exceptions;

public class ConfirmPasswordNotCorrect extends RuntimeException {
    public ConfirmPasswordNotCorrect(String message) {
        super(message);
    }
}
