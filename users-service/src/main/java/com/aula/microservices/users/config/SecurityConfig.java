package com.aula.microservices.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitamos CSRF para APIs REST
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/**").permitAll() // Actuator libre para monitorización
                .requestMatchers("/api/users/internal/**").authenticated() // Consultas internas requieren token
                .requestMatchers("/api/users/**").authenticated() // Consultas públicas requieren token
                .anyRequest().denyAll() // Por seguridad, cerramos todo lo demás
            )
            .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return http.build();
    }
}
