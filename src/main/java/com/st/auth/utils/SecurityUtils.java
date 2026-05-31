package com.st.auth.utils;

import com.st.auth.config.AuthenticatedUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;

public final class SecurityUtils {

    public static Integer getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assert authentication != null;
        return  ((AuthenticatedUser) Objects.requireNonNull(authentication.getPrincipal())).getUserId();
    }

//    public static boolean isAdmin() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        assert authentication != null;
//        String role = ((User) Objects.requireNonNull(authentication.getPrincipal())).getRole();
//        return  "ADMIN".equals(role);
//    }

}
