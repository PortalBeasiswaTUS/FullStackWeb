package com.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // Disable CSRF untuk memudahkan testing API, sesuaikan di production
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/users/registerStep1",
                    "/api/users/registerStep2",
                    "/uploads/**",
                    "/css/**",
                    "/js/**",
                    "/",
                    "/**.html"
                ).permitAll() // Izinkan akses bebas untuk register dan resource statis
                .anyRequest().authenticated() // Endpoint lain butuh otentikasi
            )
.csrf(csrf -> csrf.disable());// Aktifkan HTTP Basic Auth jika perlu (bisa dihapus jika tidak mau)

        return http.build();
    }
}