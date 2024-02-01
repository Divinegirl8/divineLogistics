package org.logistics.exceptions;

public class LastNameContainsNumberError extends RuntimeException {
    public LastNameContainsNumberError(String message){
        super(message);
    }
}
