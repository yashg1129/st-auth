package com.st.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public class RegisterRequest {

    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @Email @NotBlank String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotBlank String email) {
        this.email = email;
    }

    public @NotBlank @Size(min = 6) String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Size(min = 6) String password) {
        this.password = password;
    }

    public @NotBlank String getGender() {
        return gender;
    }

    public void setGender(@NotBlank String gender) {
        this.gender = gender;
    }
}