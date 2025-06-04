package com.demo.controller;

import com.demo.model.User;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Mendapatkan semua data user
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Enkripsi password sebelum menyimpan ke database
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        // Simpan user ke dalam database
        userRepository.save(user);

        return ResponseEntity.ok("{\"success\": true}");
    }

    @PostMapping("/uploadProfileImage/{id}")
public ResponseEntity<String> uploadProfileImage(@PathVariable Long id, @RequestParam("profileImage") MultipartFile profileImage) {
    try {
        // Mencari user yang ada berdasarkan ID
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        // Mengonversi gambar ke dalam byte array
        byte[] imageBytes = profileImage.getBytes();
        user.setProfileImage(imageBytes); // Menyimpan gambar dalam bentuk byte array

        // Menyimpan update ke database
        userRepository.save(user);

        return ResponseEntity.ok("Profile image uploaded successfully!");
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image.");
    }
}
}
