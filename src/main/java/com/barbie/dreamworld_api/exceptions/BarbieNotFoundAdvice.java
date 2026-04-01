package com.barbie.dreamworld_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class BarbieNotFoundAdvice {

    @ExceptionHandler(BarbieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String barbieNotFoundHandler(BarbieNotFoundException ex) {
        return ex.getMessage();
    }
}