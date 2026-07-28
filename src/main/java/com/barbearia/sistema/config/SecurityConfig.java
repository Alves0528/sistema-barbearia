package com.barbearia.sistema.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Desativa para permitir os formulários locais a funcionarem

                // Liberação: Avisa pro Spring parar de bloquear as URLs
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()
                )

                // desabilita a tela de login branca padrão do Spring!
                .formLogin(AbstractHttpConfigurer::disable)

                // desabilita o pop-up nativo de login do navegador
                .httpBasic(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
