package com.demo.repository;

import com.demo.model.GpaRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GpaRecordRepository extends JpaRepository<GpaRecord, Long> {
    List<GpaRecord> findByUserId(Long userId);
}
