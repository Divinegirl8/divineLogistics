package org.logistics.exceptions;

public class PasswordNotValid extends RuntimeException {
    public PasswordNotValid(String message) {
        super(message);
    }
}
