package com.st.auth.util;

import com.st.auth.enums.Role;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public class AuthorityUtils {

    public static Set<SimpleGrantedAuthority> getAuthorities(Role role) {
        Set<SimpleGrantedAuthority> permissions =
                role.getPermissions().stream()
                        .map(p -> new SimpleGrantedAuthority(p.name()))
                        .collect(Collectors.toSet());

        permissions.add(new SimpleGrantedAuthority("ROLE_" + role.name()));

        return permissions;
    }
}
