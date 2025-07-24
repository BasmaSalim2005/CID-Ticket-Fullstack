package com.basma.Demo1.exceptions;

public class UserAlreadyExistsExeception extends RuntimeException {
    public UserAlreadyExistsExeception(String message) {
        super(message);
    }
}
