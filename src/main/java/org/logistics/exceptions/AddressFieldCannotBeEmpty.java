package org.logistics.exceptions;

public class AddressFieldCannotBeEmpty extends RuntimeException {
    public AddressFieldCannotBeEmpty(String message) {
        super(message);
    }
}
