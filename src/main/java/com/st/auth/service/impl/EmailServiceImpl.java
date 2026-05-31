package com.st.auth.service.impl;

import com.st.auth.entity.EmailRequest;
import com.st.auth.enums.EmailType;
import com.st.auth.record.EmailRecord;
import com.st.auth.record.SendLoginOtpRequest;
import com.st.auth.repository.UserRepository;
import com.st.auth.service.EmailService;
import com.st.auth.service.OtpService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserRepository userRepository;

    @Value("${st.contact.us.email}")
    private String contactUsEmail;

    @Override
    public void sendSimpleEmail(EmailRequest dto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("skillstute.com@gmail.com");
        message.setSubject("Enquiry");
        String body = "\nName: " + dto.getName() + "\nEmail: " + dto.getEmail() + "\nMessage: " + dto.getMessage();
        message.setText(body);
        javaMailSender.send(message);
    }

    @Override
    public void sendEmail(SendLoginOtpRequest request) {
        switch (request.type()) {
            case LOGIN_OTP -> {
//                boolean userExist = userRepository.existsByEmail(request.email());
//                if(userExist) {
                    sendEmail(getLoginOtpRequest(request, otpService.getOtp(request.email())), "email/login-otp");
//                } else {
//                    throw new UsernameNotFoundException("This email address is not registered with us.");
//                }
            }
            case REGISTRATION_OTP -> sendEmail(getRegistrationOtpRequest(request, otpService.getOtp(request.email())), "email/registration-otp");
            case FORGOT_PASSWORD_OTP -> sendEmail(getForgotPasswordOtpRequest(request), "email/forgot-password-otp");
           // case CONTACT_FORM -> sendEmail(getContactFormRequest(request), "email/contact-form");
//            case NOTIFICATION -> sendEmail(getNotificationRequest(request), "email/notification");
        }
    }

    private EmailRecord getLoginOtpRequest(SendLoginOtpRequest request, String otp) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("otp", otp);
        return new EmailRecord(request.email(), "OTP login", EmailType.LOGIN_OTP, variables);
    }

    private EmailRecord getRegistrationOtpRequest(SendLoginOtpRequest request, String otp) {
        return new EmailRecord(
                request.email(),
                "Verify your SkillsTute account",
                EmailType.REGISTRATION_OTP,
                Map.of(
                        //"name", request.name(),
                        "otp", otp,
                        "expiryMinutes", 10
                )
        );
    }

    private EmailRecord getForgotPasswordOtpRequest(SendLoginOtpRequest request) {
        return new EmailRecord(
                request.email(),
                "Verify your SkillsTute account",
                EmailType.FORGOT_PASSWORD_OTP,
                Map.of(
                        //"name", request.name(),
                        "otp", otpService.getOtp(request.email()),
                        "expiryMinutes", 10
                )
        );
    }

    private EmailRecord getContactFormRequest(EmailRequest request) {
        return new EmailRecord(
                contactUsEmail,
                "Feedback / Enquiry",
                EmailType.CONTACT_FORM,
                Map.of(
                        "name", request.getName(),
                        "email", request.getEmail(),
                        "message", request.getMessage()
                )
        );
    }

    private EmailRecord getNotificationRequest(EmailRequest request) {
        EmailRecord record = null;
//                new EmailRecord(
//                user.getEmail(),
//                "Verify your SkillsTute account",
//                EmailType.NOTIFICATION,
//                Map.of(
//                        "name", request.getName(),
//                        "otp", otp,
//                        "expiryMinutes", 10
//                )
//        );
        return record;
    }

    private void sendEmail(EmailRecord record, String template) {
        Context context = new Context();
        context.setVariables(record.variables());
        String html = templateEngine.process(template, context);
        System.out.println("Start.........................");
        System.out.println(html);
        System.out.println("End............................");
    }

    private void sendHtmlEmail(String to, String subject, String html) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
