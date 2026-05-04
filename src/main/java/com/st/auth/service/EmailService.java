package com.st.auth.service;

import com.st.auth.entity.EmailDto;

public interface EmailService {
    void sendSimpleEmail(EmailDto emailDto);
}
