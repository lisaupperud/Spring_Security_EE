package com.liluppis.spring_security.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class CustomUserDetails implements UserDetails {

    private final CustomUser customUser;

    public CustomUserDetails(CustomUser customUser) {
        this.customUser = customUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        final Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();

        customUser.getUserRoles().forEach(
                userRole -> grantedAuthorities.addAll(userRole.getUserAuthorities())
        );

        return Collections.unmodifiableSet(grantedAuthorities);
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
