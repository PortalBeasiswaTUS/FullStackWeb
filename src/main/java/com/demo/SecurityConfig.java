package com.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain; // Tambahkan ini


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Menyediakan PasswordEncoder untuk enkripsi password
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests()  // Gunakan authorizeHttpRequests() untuk menggantikan authorizeRequests()
                .requestMatchers("/api/auth/register", "/api/auth/login", "/RegisterStep1.html", "/RegisterStep2.html", "/static/**").permitAll()  // Endpoints tanpa autentikasi
                .anyRequest().authenticated()  // Semua request lain harus terautentikasi
            .and()
            .csrf().disable();  // Nonaktifkan CSRF jika tidak diperlukan

        return http.build();  // Kembalikan konfigurasi HTTP yang sudah dibangun
    }
}

