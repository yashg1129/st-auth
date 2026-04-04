package com.st.auth.enums;

import java.util.Set;

public enum Role {

    ROLE_ADMIN(Set.of(
            Permission.QUESTION_CREATE,
            Permission.QUESTION_APPROVE,
            Permission.QUESTION_REJECT,
            Permission.QUESTION_DELETE
    )),

    MODERATOR(Set.of(
            Permission.QUESTION_CREATE,
            Permission.QUESTION_APPROVE,
            Permission.QUESTION_REJECT
    )),

    ROLE_USER(Set.of(
            Permission.QUESTION_CREATE
    ));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }
}