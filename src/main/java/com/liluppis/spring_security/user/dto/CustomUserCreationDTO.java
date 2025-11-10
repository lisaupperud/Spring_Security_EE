package com.liluppis.spring_security.user.dto;

import com.liluppis.spring_security.user.authority.UserRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record CustomUserCreationDTO(
        @Size(min = 2, max = 25, message = "Username length should be between 2-25")
        @NotBlank(message = "Username may not contain whitespaces or be blank")
        String username,

        @Pattern(regexp = "^" + "(?=.*[a-z])" + // at least one lowercase letter
                "(?=.*[A-Z])" + // at least one uppercase letter
                "(?=.*[0-9])" + // at least one digit
                "(?=.*[ @$!%*?&])" + // at least one special character
                ".+$", message = "Password must contain at least one uppercase, one lowercase, one digit, and one special character")
        @Size(max = 80, message = "Maximum length of password encoded")
        String password,

        @NotNull boolean isAccountNonExpired,
        @NotNull boolean isAccountNonLocked,
        @NotNull boolean isCredentialsNonExpired,
        @NotNull boolean isEnabled,

        @NotNull
        @Pattern(
                regexp = "^(GUEST|USER|ADMIN)$",        // TODO - Util function, loop through each ENUM: secure type-safety
                message = "Must be a valid role"
        )
        Set<UserRole> roles
) {
}
