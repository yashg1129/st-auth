package com.st.auth.service.impl;

import com.st.auth.dto.OtpData;
import com.st.auth.service.OtpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpServiceImpl implements OtpService {

    private static final SecureRandom random = new SecureRandom();
    private final ConcurrentHashMap<String, OtpData> otpStore = new ConcurrentHashMap<>();

    @Value("${st.generate.otp}")
    private Integer otpLength;

    public String getOtp(String email) {
        OtpData otpData = new OtpData(generateOtp(), LocalDateTime.now().plusMinutes(5));
        otpStore.put(email, otpData);
        return otpData.getOtp();
    }

    private String generateOtp() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < otpLength; i++) {
            otp.append(random.nextInt(10)); // 0–9
        }
        return otp.toString();
    }

    public boolean verifyOtp(String email, String otp) {
        OtpData data = otpStore.get(email);
        if (data == null) return false;
        if (data.getExpiry().isBefore(LocalDateTime.now())) return false;
        return data.getOtp().equals(otp);
    }

}
