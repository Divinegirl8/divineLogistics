package org.logistics.exceptions;

public class LoginCredentialNotValid extends RuntimeException {
    public LoginCredentialNotValid() {
        super("login credentials is invalid");
    }
}
