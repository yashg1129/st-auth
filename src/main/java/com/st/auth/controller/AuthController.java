package com.st.auth.controller;

import com.st.auth.dto.AuthResponse;
import com.st.auth.dto.LoginRequest;
import com.st.auth.dto.RegisterRequest;
import com.st.auth.entity.EmailRequest;
import com.st.auth.record.SendLoginOtpRequest;
import com.st.auth.service.AuthService;
import com.st.auth.service.EmailService;
import com.st.auth.service.OtpService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService authService;
    private final EmailService emailService;
    private final OtpService otpService;

    public AuthController(AuthService authService, EmailService emailService, OtpService otpService) {
        this.authService = authService;
        this.emailService = emailService;
        this.otpService = otpService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request.getEmail(), request.getPassword()));
    }

    @PostMapping("/send-login-otp")
    public ResponseEntity<String> loginOtp(@Valid @RequestBody SendLoginOtpRequest request) {
        emailService.sendEmail(request);
        return ResponseEntity.ok("OTP sent successfully");
    }

    @PostMapping("/verify-login-otp")
    public AuthResponse verifyLoginOtp(@RequestBody EmailRequest request) {
        return authService.verifyLoginOtp(request.getEmail(), request.getOtp());
    }

    @GetMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Public API working");
    }
}