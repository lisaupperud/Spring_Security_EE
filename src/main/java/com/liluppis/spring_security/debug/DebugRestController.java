package com.liluppis.spring_security.debug;

import com.liluppis.spring_security.user.CustomUser;
import com.liluppis.spring_security.user.CustomUserRepository;
import com.liluppis.spring_security.user.authority.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/debug")
public class DebugRestController {

    private final PasswordEncoder encoder;
    private final CustomUserRepository customUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DebugRestController(PasswordEncoder encoder, CustomUserRepository customUserRepository, PasswordEncoder passwordEncoder) {
        this.encoder = encoder;
        this.customUserRepository = customUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public ResponseEntity<String> testBCryptEncoding(
            @RequestParam(value = "message") String message
    ) {
        String obfuscatedMessage = encoder.encode(message);
        return ResponseEntity.ok().body(
                "Message was: " + message + " and was hashed into: " + obfuscatedMessage
        );
    }

    @GetMapping("/create-debug-user")
    public ResponseEntity<String> createDebugUser() {

        try {
            customUserRepository.save(
                    new CustomUser(
                            "Benny",
                            passwordEncoder.encode("123"),
                            true,
                            true,
                            true,
                            true,
                            Set.of(UserRole.ADMIN)
                    )
            );

            return ResponseEntity.ok().body("New User was created");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().body("Username already exists" + e);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Something went wrong" + e);
        } finally {
            System.out.println("Process: Creating a new debug User, finishing...");
        }
    }
}
