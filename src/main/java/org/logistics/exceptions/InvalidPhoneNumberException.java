package org.logistics.exceptions;

import com.google.i18n.phonenumbers.NumberParseException;

public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
