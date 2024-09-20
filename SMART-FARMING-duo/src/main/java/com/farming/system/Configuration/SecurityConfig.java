package com.farming.system.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF for simplicity
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/admin/register").permitAll() // Allow access to admin registration
                .requestMatchers("/admin/**").hasRole("ADMIN") // Restrict other admin routes to admins
                .requestMatchers("/user/**").hasRole("USER") // Restrict user routes to users
                .anyRequest().permitAll() // Allow public access to other endpoints
            )
            .formLogin().disable(); // Disable default form login
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
