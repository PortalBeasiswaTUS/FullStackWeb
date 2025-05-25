package com.demo.controller;

import com.demo.model.User;
import com.demo.model.LoginLog;
import com.demo.model.PasswordUpdateLog;
import com.demo.repository.UserRepository;
import com.demo.repository.LoginLogRepository;
import com.demo.repository.PasswordUpdateLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired private UserRepository userRepo;
    @Autowired private LoginLogRepository loginRepo;
    @Autowired private PasswordUpdateLogRepository pwUpdateRepo;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/login-logs")
    public List<LoginLog> getLoginLogs() {
        return loginRepo.findAllByOrderByLoginTimeDesc();
    }

    @GetMapping("/password-updates")
    public List<PasswordUpdateLog> getPwUpdates() {
        return pwUpdateRepo.findAllByOrderByUpdatedAtDesc();
    }
}
