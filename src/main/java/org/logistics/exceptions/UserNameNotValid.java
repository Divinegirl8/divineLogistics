package org.logistics.exceptions;

public class UserNameNotValid extends RuntimeException {
    public UserNameNotValid(String message) {
        super(message);
    }
}
