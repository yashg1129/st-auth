package com.st.auth.record;

import com.st.auth.enums.EmailType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SendLoginOtpRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Enter valid email")
        String email,

        @NotNull(message = "Email Type is required")
        EmailType type
) {}
