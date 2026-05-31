package com.st.auth.service;

import com.st.auth.entity.EmailRequest;
import com.st.auth.record.SendLoginOtpRequest;

public interface EmailService {

    void sendSimpleEmail(EmailRequest emailDto);
    void sendEmail(SendLoginOtpRequest request);
}
