package com.basma.Demo1.exceptions.handler;


import com.basma.Demo1.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {
    private static final String ERROR_MESSAGE = "errorMessage";
    // User exceptions
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> UserNotFoundException(UserNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ERROR_MESSAGE, ex.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(UserAlreadyExistsExeception.class)
    public Map<String, String> UserAlreadyExistsException(UserAlreadyExistsExeception ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ERROR_MESSAGE, ex.getMessage());
        return errorMap;
    }
    // Tic exceptions
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(TicketNotFoundException.class)
    public Map<String, String> TicketNotFoundException(TicketNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ERROR_MESSAGE, ex.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(TicketAlreadyExistsExeception.class)
    public Map<String, String> TicketAlreadyExistsException(TicketAlreadyExistsExeception ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ERROR_MESSAGE, ex.getMessage());
        return errorMap;
    }
    // Feature exceptions
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(FeatureNotFoundException.class)
    public Map<String, String> FeatureNotFoundException(FeatureNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ERROR_MESSAGE, ex.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(FeatureAlreadyExistsException.class)
    public Map<String, String> FeatureAlreadyExistsException(FeatureAlreadyExistsException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ERROR_MESSAGE, ex.getMessage());
        return errorMap;
    }
    // Feedback exceptions
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(FeedbackNotFoundException.class)
    public Map<String, String> FeedbackNotFoundException(FeedbackNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ERROR_MESSAGE, ex.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(FeedbackAlreadyExistsException.class)
    public Map<String, String> FeedbackAlreadyExistsException(FeedbackAlreadyExistsException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ERROR_MESSAGE, ex.getMessage());
        return errorMap;
    }
    // Application exceptions
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(ApplicationNotFoundException.class)
    public Map<String, String> ApplicationNotFoundException(ApplicationNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ERROR_MESSAGE, ex.getMessage());
        return errorMap;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(ApplicationAlreadyExistsException.class)
    public Map<String, String> ApplicationAlreadyExistsException(ApplicationAlreadyExistsException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ERROR_MESSAGE, ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public Map<String, String> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ERROR_MESSAGE, ex.getMessage());
        return errorMap;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @org.springframework.web.bind.annotation.ExceptionHandler(CredentialsNotCorrectException.class)
    public Map<String, String> handleInvalidArgument(CredentialsNotCorrectException ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put(ERROR_MESSAGE, ex.getMessage());
        return errorMap;
    }
}