package com.demo.controller;

import com.demo.dto.LoginRequest;
import com.demo.model.User;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:8080") // Untuk frontend yang berjalan di URL ini
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Menggunakan PasswordEncoder yang sudah dikonfigurasi

    // Register user dan enkripsi password
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Cek apakah email sudah digunakan
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.badRequest().body("{\"error\": \"Email sudah digunakan.\"}");
        }

        // Enkripsi password sebelum disimpan
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        // Simpan user di database
        userRepository.save(user);

        return ResponseEntity.ok("{\"success\": true, \"message\": \"User berhasil didaftarkan.\"}");
    }

    // Login user
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isPresent()) {
            User user = userOpt.get();

            // Cek apakah password cocok
            if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
                return ResponseEntity.ok("{\"success\": true, \"message\": \"Login Berhasil\"}");
            } else {
                return ResponseEntity.status(401).body("{\"error\": \"Password salah\"}");
            }
        }

        return ResponseEntity.status(404).body("{\"error\": \"User tidak ditemukan\"}");
    }
}
