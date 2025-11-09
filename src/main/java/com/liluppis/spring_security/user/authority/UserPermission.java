package com.liluppis.spring_security.user.authority;

public enum UserPermission {

    READ("READ"),
    WRITE("WRITE"),
    DELETE("DELETE");

    private final String permission;

    UserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
