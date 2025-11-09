package com.liluppis.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Security Configuration
 * The configuration class is where we will configure our user,
 * and also information and data that will be exceeded for the application.
 */

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    // CREATE DEBUG USER
    @Bean       // Specifies that this is a Spring related Object
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("Benny")      // Username for the Users account
                        .password("123")        // Password for the Users account
                        .roles("USER")          // Assign roles for limitation
                        .build();               // Build the User as the last step

        return new InMemoryUserDetailsManager(user);        // DONT FORGET to return the User
    }
}
