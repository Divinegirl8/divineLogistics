package org.logistics.exceptions;

public class LoginTimeoutException extends RuntimeException {
    public LoginTimeoutException(String message) {
        super(message);
    }
}
