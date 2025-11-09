package com.liluppis.spring_security.debug;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/debug")
public class DebugRestController {

    private final PasswordEncoder encoder;

    @Autowired
    public DebugRestController(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @GetMapping
    public ResponseEntity<String> testBCryptEncoding(
            @RequestParam(value = "message") String message
    ) {
        String obfuscatedMessage =  encoder.encode(message);
        return ResponseEntity.ok().body(
                "Message was: " + message + " and was hashed into: " +  obfuscatedMessage
        );
    }
}
