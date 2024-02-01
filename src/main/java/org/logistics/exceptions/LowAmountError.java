package org.logistics.exceptions;

public class LowAmountError extends RuntimeException {
    public LowAmountError(String message) {
        super(message);
    }
}
