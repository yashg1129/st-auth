package com.st.auth.record;

import com.st.auth.enums.EmailType;

import java.util.Map;

public record EmailRecord(String to,
                          String subject,
                          EmailType type,
                          Map<String, Object> variables) {
}
