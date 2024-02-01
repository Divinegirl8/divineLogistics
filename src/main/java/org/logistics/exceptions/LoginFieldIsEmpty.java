package org.logistics.exceptions;

public class LoginFieldIsEmpty extends RuntimeException {
    public LoginFieldIsEmpty(String message) {
        super(message);
    }
}
