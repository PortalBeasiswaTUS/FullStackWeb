package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages = "com.demo")  // Pastikan scan base package sesuai
public class PortalbeasiswaApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortalbeasiswaApplication.class, args);
    }

    
}
