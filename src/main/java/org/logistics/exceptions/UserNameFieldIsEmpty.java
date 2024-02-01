package org.logistics.exceptions;

public class UserNameFieldIsEmpty extends RuntimeException {
    public UserNameFieldIsEmpty(String message) {
        super(message);
    }
}
