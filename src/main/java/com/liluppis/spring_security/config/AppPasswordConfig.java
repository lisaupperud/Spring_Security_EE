package com.liluppis.spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/** Password Configuration
 *      A class where we can adjust which type of encoder we want to use.
 *      This is using the BCrypt Encoder.
 * */

@Configuration      // Tells Spring that we should override a configuration with this custom one
public class AppPasswordConfig {

    @Bean       // Marks the function as a Spring Object
    public PasswordEncoder defaultPasswordEncoder() {
        return new BCryptPasswordEncoder(16);     // Our hashing functionality // Strength = Iterations
    }
}
