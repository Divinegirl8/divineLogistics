package org.logistics.exceptions;

public class LogoutFieldIsEmpty extends RuntimeException {
    public LogoutFieldIsEmpty(String message) {
        super(message);
    }
}
