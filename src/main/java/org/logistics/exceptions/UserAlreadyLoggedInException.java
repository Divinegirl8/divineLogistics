package org.logistics.exceptions;

public class UserAlreadyLoggedInException extends RuntimeException {
    public UserAlreadyLoggedInException(){
        super("user is already logged in");
    }
}
