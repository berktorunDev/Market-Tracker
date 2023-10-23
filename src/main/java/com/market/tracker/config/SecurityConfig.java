package com.market.tracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Define a Bean for BCryptPasswordEncoder
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Define a SecurityFilterChain for configuring security
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // Disable Cross-Site Request Forgery (CSRF) protection
        http.csrf(csrf -> csrf.disable());

        // Authorize all HTTP requests, allowing any request to be permitted
        http.authorizeHttpRequests((auth) -> auth.anyRequest().permitAll());

        // Return the configured security filter chain
        return http.build();
    }
}