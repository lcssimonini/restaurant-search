package com.restaurant.searchrank.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidFilterException.class)
    public ResponseEntity<ApiError> handleMethodArgumentTypeMismatch(InvalidFilterException ex) {
        return new ResponseEntity<>(new ApiError(ex.getLocalizedMessage()), BAD_REQUEST);
    }
}
