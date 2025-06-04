package com.demo;  // Bisa di folder demo, atau com.demo.config

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080") // Ganti dengan frontend kamu
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Metode HTTP yang diizinkan
                .allowedHeaders("*") // Mengizinkan semua header
                .allowCredentials(true); // Mengizinkan kredensial
    }
}
