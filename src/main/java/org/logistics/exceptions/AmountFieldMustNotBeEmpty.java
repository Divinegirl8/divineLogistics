package org.logistics.exceptions;

public class AmountFieldMustNotBeEmpty extends RuntimeException {
    public AmountFieldMustNotBeEmpty(String message) {
        super(message);
    }
}
