package com.liluppis.spring_security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* WebMvcConfiguration
 *   - A Interface
 *   - Open up the possibility to apply new structuring of HTML files.
 *   - Defines callback methods to customize configuration for Spring MVC.
 *   - Enables through @EnableWebMvc
 * */

@Configuration
@EnableWebMvc
public class AppWebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // adding a field with the method
        registry.addViewController("/").setViewName("home-page"); // setting the name
        registry.addViewController("/admin").setViewName("admin-page");
        /*registry.addViewController("/login").setViewName("login");
        registry.addViewController("/register").setViewName("register-page");
        registry.addViewController("/logout").setViewName("logout");
        registry.addViewController("/user").setViewName("user-page");*/
    }
}
