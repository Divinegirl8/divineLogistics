package org.logistics.exceptions;

public class CountryFieldIsEmpty extends RuntimeException {
    public CountryFieldIsEmpty(String message) {
        super(message);
    }
}
