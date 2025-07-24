package com.basma.Demo1.feedback.exceptions;

public class FeedbackAlreadyExistsException extends RuntimeException {
    public FeedbackAlreadyExistsException(String message) {
        super(message);
    }
}
