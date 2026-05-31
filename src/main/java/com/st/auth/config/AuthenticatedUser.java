package com.st.auth.config;

public class AuthenticatedUser {

    private final Integer userId;
    private final String email;

    public AuthenticatedUser(Integer userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

}
