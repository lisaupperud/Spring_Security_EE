package com.liluppis.spring_security.config;

import com.liluppis.spring_security.user.authority.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Security Configuration
 * The configuration class is where we will configure our user,
 * and also information and data that will be exceeded for the application.
 */

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    private final PasswordEncoder encoder;

    @Autowired
    public AppSecurityConfig(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults());

        return http.build();
    }

    // CREATE DEBUG USER
    @Bean       // Specifies that this is a Spring related Object
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user =
                User
                        .withUsername("Benny")      // Username for the Users account
                        .password(passwordEncoder.encode("123"))        // Password for the Users account
                        // .roles("USER", "ADMIN")          // Assign roles for limitation
                        .authorities(UserRole.ADMIN.getUserAuthorities()) // Instead of hardcoding the Roles, we use our Enums!
                        .build();               // Build the User as the last step

        return new InMemoryUserDetailsManager(user);        // DONT FORGET to return the User
    }

}
