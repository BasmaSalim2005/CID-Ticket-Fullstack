package com.basma.Demo1.exceptions;

public class FeatureAlreadyExistsException extends RuntimeException {
    public FeatureAlreadyExistsException(String message) {
        super(message);
    }
}
