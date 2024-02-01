package org.logistics.exceptions;

public class PostalCodeIsEmpty extends RuntimeException {
    public PostalCodeIsEmpty(String message) {
        super(message);
    }
}
