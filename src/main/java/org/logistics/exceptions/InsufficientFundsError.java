package org.logistics.exceptions;

public class InsufficientFundsError extends RuntimeException {
    public InsufficientFundsError(String message) {
        super(message);
    }
}
