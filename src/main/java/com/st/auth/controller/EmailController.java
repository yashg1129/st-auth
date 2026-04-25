package com.st.auth.controller;

import com.st.auth.entity.EmailDto;
import com.st.auth.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService service;

    @PostMapping("/send")
    String send(@RequestBody EmailDto email) {
        service.sendSimpleEmail(email);
        return "Email successfully sent";
    }
}
