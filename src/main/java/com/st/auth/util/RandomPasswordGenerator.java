package com.st.auth.util;

import java.security.SecureRandom;

public class RandomPasswordGenerator {
    private static final String CHARS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generatePassword(int length) {

        SecureRandom random = new SecureRandom();

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {

            int index = random.nextInt(CHARS.length());

            password.append(CHARS.charAt(index));
        }

        return password.toString();
    }
}
