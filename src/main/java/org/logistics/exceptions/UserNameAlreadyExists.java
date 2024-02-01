package org.logistics.exceptions;

public class UserNameAlreadyExists extends RuntimeException {
    public UserNameAlreadyExists(String message) {
        super(message);
    }
}
