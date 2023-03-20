package com.app.hotels.web.controller;

import com.app.hotels.domain.exception.DateNullPointerException;
import com.app.hotels.domain.exception.IllegalDateDurationException;
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

    @ExceptionHandler(IllegalDateDurationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleIllegalDateDurationException(IllegalDateDurationException ex) {
        return "Illegal dates!";
    }

    @ExceptionHandler(DateNullPointerException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleDateNullPointerException(DateNullPointerException ex) {
        return "Insert data!";
    }

}
