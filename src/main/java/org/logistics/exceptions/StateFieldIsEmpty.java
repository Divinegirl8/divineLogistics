package org.logistics.exceptions;

public class StateFieldIsEmpty extends RuntimeException {
    public StateFieldIsEmpty(String message) {
        super(message);
    }
}
