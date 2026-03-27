package com.st.auth.exception;

public class DuplicateResourceException extends RuntimeException {

    private String message;

    public DuplicateResourceException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
