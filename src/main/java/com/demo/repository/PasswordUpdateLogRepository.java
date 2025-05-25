package com.demo.repository;

import com.demo.model.PasswordUpdateLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PasswordUpdateLogRepository extends JpaRepository<PasswordUpdateLog, Long> {
    List<PasswordUpdateLog> findAllByOrderByUpdatedAtDesc();
}
