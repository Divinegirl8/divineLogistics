package org.logistics.exceptions;

public class ParcelWeightIsNotValid extends RuntimeException {
    public ParcelWeightIsNotValid(String message) {
        super(message);
    }
}
