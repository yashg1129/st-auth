package com.st.auth.service;

public interface OtpService {

    String getOtp(String email);

    boolean verifyOtp(String email, String otp);
}
