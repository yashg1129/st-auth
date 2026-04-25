package com.st.auth.service.impl;

import com.st.auth.entity.EmailDto;
import com.st.auth.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendSimpleEmail(EmailDto dto) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo("skillstute.com@gmail.com");
        message.setSubject("Enquiry");
        String body = "\nName: " + dto.getName() + "\nEmail: " + dto.getEmail() + "\nMessage: " + dto.getMessage();
        message.setText(body);

        javaMailSender.send(message);
    }
}
