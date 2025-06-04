package com.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "login_logs")
public class LoginLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime loginTime = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "user_id")  // Foreign key yang menghubungkan ke User.id
    private User user;

    // Getters & Setters
    public Long getId() { return id; }
    public LocalDateTime getLoginTime() { return loginTime; }
    public void setLoginTime(LocalDateTime loginTime) { this.loginTime = loginTime; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
