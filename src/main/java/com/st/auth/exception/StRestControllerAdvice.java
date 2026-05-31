package com.st.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StRestControllerAdvice {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<String> exception(DuplicateResourceException ex) {
        return generateBadResponseException(ex);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> exception(InvalidCredentialsException ex) {
        return generateBadResponseException(ex);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> exception(UsernameNotFoundException ex) {
        return generateUnauthorizedResponseException(ex);
    }

    @ExceptionHandler(InvalidOtpException.class)
    public ResponseEntity<String> exception(InvalidOtpException ex) {
        return generateBadResponseException(ex);
    }

    private ResponseEntity<String> generateBadResponseException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<String> generateUnauthorizedResponseException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
