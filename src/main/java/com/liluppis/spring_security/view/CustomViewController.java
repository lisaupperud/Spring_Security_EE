package com.liluppis.spring_security.view;

import com.liluppis.spring_security.user.CustomUser;
import com.liluppis.spring_security.user.CustomUserRepository;
import com.liluppis.spring_security.user.authority.UserRole;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class CustomViewController {

    private final PasswordEncoder passwordEncoder;
    private final CustomUserRepository customUserRepository;

    @Autowired
    public CustomViewController(PasswordEncoder passwordEncoder, CustomUserRepository customUserRepository) {
        this.passwordEncoder = passwordEncoder;
        this.customUserRepository = customUserRepository;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logoutPage() {
        return "logout";
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "admin-page"; // Must Reflect .html document name
    }

    @GetMapping("/user")
    public String userPage() {
        return "user-page";
    }

    @GetMapping("/register")
    public String showAddUserForm(Model model) {
        // Best Practice: id aka AttributeName should be the same as Object name
        model.addAttribute("customUser", new CustomUser());
        return "register-page";
    }

    @PostMapping("/register")
    public String register(@Valid CustomUser customUser, BindingResult result) {

        if (result.hasErrors()) {
            return "register-page";
        }

        customUser.setPassword(
                passwordEncoder.encode(customUser.getPassword())
        );

        // TODO - ObjectMapper for shorter syntax
        customUser.setAccountNonExpired(true);
        customUser.setAccountNonLocked(true);
        customUser.setCredentialsNonExpired(true);
        customUser.setEnabled(true);

        // TODO - Handle roles graciously
        customUser.setUserRoles(
                Set.of(UserRole.ADMIN)
        );

        System.out.println("Saving CustomUser...");
        customUserRepository.save(customUser);

        return "redirect:/login"; // A function rather than an HTML page
    }
}

