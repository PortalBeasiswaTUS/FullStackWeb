package com.demo.repository;

import com.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Menambahkan query untuk mencari user berdasarkan email
    Optional<User> findByEmail(String email);
}
