package com.liluppis.spring_security.user.authority;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.liluppis.spring_security.user.authority.UserPermission.*;

public enum UserRole {

    GUEST(
            UserRoleName.GUEST.getRoleName(),
            Set.of()
    ),

    USER(
            UserRoleName.USER.getRoleName(),
            Set.of(
                    READ,
                    WRITE
            )
    ),

    ADMIN(
            UserRoleName.ADMIN.getRoleName(),
            Set.of(
                    READ,
                    WRITE,
                    DELETE
            )
    );

    private final String roleName;
    private final Set<UserPermission> userPermissions;

    UserRole(String roleName, Set<UserPermission> roles) {
        this.roleName = roleName;
        this.userPermissions = roles;
    }

    public String getRoleName() {
        return roleName;
    }

    public Set<UserPermission> getUserPermissions() {
        return userPermissions;
    }

    // SimpleGrantedAuthority Implementation
    public List<SimpleGrantedAuthority> getUserAuthorities() {

        // Create Array List
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        // Add Roles
        authorities.add(new SimpleGrantedAuthority(this.roleName));     // this == the choice made AFTER UserRole (e.g: UserRole.ADMIN)

        // Add Permissions
        authorities.addAll(
                this.userPermissions.stream().map(
                        userPermission -> new SimpleGrantedAuthority(
                                userPermission.getPermission())
                ).toList()
        );

        return authorities;
    }
}
