package com.app.hotels.web.controller;

import com.app.hotels.domain.exception.ResourceAlreadyExistsException;
import com.app.hotels.domain.exception.ResourceDoesNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {
        return "registration";
    }

    @ExceptionHandler(ResourceDoesNotExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleResourceDoesNotExistException(ResourceDoesNotExistException ex) {
        return "hotels";
    }
}
