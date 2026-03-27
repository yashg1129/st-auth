package com.st.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StRestControllerAdvice {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<String> exception(DuplicateResourceException ex) {
        return generateBadRequestException(ex);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> exception(InvalidCredentialsException ex) {
        return generateBadRequestException(ex);
    }

    private ResponseEntity<String> generateBadRequestException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
