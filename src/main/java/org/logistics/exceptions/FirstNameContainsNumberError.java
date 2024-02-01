package org.logistics.exceptions;

public class FirstNameContainsNumberError extends RuntimeException{
    public FirstNameContainsNumberError(String message) {
        super(message);
    }
}
