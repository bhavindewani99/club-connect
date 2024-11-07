package com.clubconnect.clubconnect_backend.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    // Constructor with three parameters
    public ResourceNotFoundException(String entity, String field, Long value) {
        super(String.format("%s not found with %s: '%s'", entity, field, value));
    }
}
