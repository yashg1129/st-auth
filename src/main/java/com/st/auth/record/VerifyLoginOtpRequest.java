package com.st.auth.record;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record VerifyLoginOtpRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Enter valid email")
        String email,

        @NotBlank(message = "OTP is required")
        String otp
) {}