package com.basma.Demo1.feature.exceptions;

public class FeatureAlreadyExistsException extends RuntimeException {
    public FeatureAlreadyExistsException(String message) {
        super(message);
    }
}
