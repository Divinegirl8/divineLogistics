package org.logistics.exceptions;

public class PhoneNumberFieldCannotBeEmpty extends RuntimeException {
    public PhoneNumberFieldCannotBeEmpty(String message) {
        super(message);
    }
}
