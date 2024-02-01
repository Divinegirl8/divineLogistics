package org.logistics.exceptions;

public class LogoutCredentialNotValid extends RuntimeException {
    public LogoutCredentialNotValid(){
        super("logout credentials is not valid");
    }
}
