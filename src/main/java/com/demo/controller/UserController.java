package com.demo.controller;

import com.demo.dto.RegisterStep1DTO;
import com.demo.dto.RegisterStep2DTO;
import com.demo.model.User;
import com.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:8080") 
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private static final String UPLOAD_DIR = "uploads/transcripts/";

    @PostMapping("/registerStep1")
    public ResponseEntity<?> registerStep1(@Valid @RequestBody RegisterStep1DTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        // Cek email dan nim sudah ada tidak
        if (userRepository.existsByEmail(dto.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already exists"));
        }
        if (userRepository.existsByNim(dto.getNim())) {
            return ResponseEntity.badRequest().body(Map.of("error", "NIM already exists"));
        }

        User user = new User();
        user.setFullName(dto.getFullName());
        user.setNim(dto.getNim());
        user.setYearOfEntry(dto.getYearOfEntry());
        user.setStudyProgram(dto.getStudyProgram());
        user.setFaculty(dto.getFaculty());
        user.setEmail(dto.getEmail());
        user.setWhatsapp(dto.getWhatsapp());
        user.setPassword(dto.getPassword()); // NOTE: sebaiknya hash password sebelum simpan
        user.setAddress(dto.getAddress());
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        User savedUser = userRepository.save(user);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("userId", savedUser.getId());
        response.put("message", "Registration step 1 completed successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/registerStep2")
    public ResponseEntity<?> registerStep2(
            @RequestParam("full_name") String fullName,
            @RequestParam("nim") String nim,
            @RequestParam("year_of_entry") Integer yearOfEntry,
            @RequestParam("study_program") String studyProgram,
            @RequestParam("faculty") String faculty,
            @RequestParam("email") String email,
            @RequestParam("whatsapp") String whatsapp,
            @RequestParam("password") String password,
            @RequestParam("address") String address,
            @Valid RegisterStep2DTO dto,
            @RequestParam("transcript_path") MultipartFile transcriptPath) {

        // Validasi semester1 sudah dilakukan oleh @Valid DTO, tambahan validasi file:
        if (transcriptPath.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Please upload your transcript"));
        }

        String contentType = transcriptPath.getContentType();
        if (contentType == null || !contentType.equals("application/pdf")) {
            return ResponseEntity.badRequest().body(Map.of("error", "Please upload a PDF file"));
        }

        if (transcriptPath.getSize() > 5 * 1024 * 1024) { // 5 MB
            return ResponseEntity.badRequest().body(Map.of("error", "File size must be less than 5MB"));
        }

        try {
            Path uploadDir = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            String fileName = System.currentTimeMillis() + "_" + transcriptPath.getOriginalFilename();
            Path filePath = uploadDir.resolve(fileName);
            Files.copy(transcriptPath.getInputStream(), filePath);
            String fileUrl = "/uploads/transcripts/" + fileName;

            User user = userRepository.findByNim(nim);
            if (user == null) {
                return ResponseEntity.badRequest().body(Map.of("error", "User not found"));
            }

            // Update data GPA dari DTO
            user.setSemester1(dto.getSemester1());
            user.setSemester2(dto.getSemester2());
            user.setSemester3(dto.getSemester3());
            user.setSemester4(dto.getSemester4());
            user.setSemester5(dto.getSemester5());
            user.setSemester6(dto.getSemester6());
            user.setSemester7(dto.getSemester7());
            user.setSemester8(dto.getSemester8());
            user.setTranscriptPath(fileUrl);
            user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

            userRepository.save(user);

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "Registration completed successfully");
            response.put("redirectUrl", "/AdminUser.html");

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(Map.of("error", "Error saving transcript: " + e.getMessage()));
        }
    }
}