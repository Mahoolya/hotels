package com.app.hotels.domain.exception;

public class ResourceDoesNotExistException extends RuntimeException {

    public ResourceDoesNotExistException(String message) {
        super(message);
    }

}
