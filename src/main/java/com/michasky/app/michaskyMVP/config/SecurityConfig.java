package com.michasky.app.michaskyMVP.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Permitir H2 Console
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().permitAll() // MVP: todo abierto
                )
                // Desactivar CSRF para H2
                .csrf(csrf -> csrf.disable())
                // Permitir frames (H2 usa iframes)
                .headers(headers -> headers.frameOptions(frame -> frame.disable()))
                // Desactivar login por defecto
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
