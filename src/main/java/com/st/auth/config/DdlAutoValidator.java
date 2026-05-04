package com.st.auth.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

public class DdlAutoValidator
        implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext context) {
        Environment env = context.getEnvironment();
        String profile = env.getProperty("spring.profiles.active");
        String ddlAuto = env.getProperty("spring.jpa.hibernate.ddl-auto");

        if (ddlAuto != null && (ddlAuto.toLowerCase().contains("create") || ddlAuto.toLowerCase().contains("drop")) && !"dev".equals(profile)) {
            throw new RuntimeException(
                    "Application blocked: spring.jpa.hibernate.ddl-auto=create is not allowed."
            );
        }
    }
}