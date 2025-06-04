package com.demo.controller;

import com.demo.model.GpaRecord;
import com.demo.repository.GpaRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gpa")
public class GpaController {

    @Autowired
    private GpaRecordRepository gpaRecordRepository;

    // Menambahkan data GPA untuk user
    @PostMapping("/add")
    public GpaRecord addGpaRecord(@RequestBody GpaRecord gpaRecord) {
        return gpaRecordRepository.save(gpaRecord);
    }

    // Mendapatkan data GPA berdasarkan user ID
    @GetMapping("/user/{userId}")
    public List<GpaRecord> getGpaByUser(@PathVariable Long userId) {
        return gpaRecordRepository.findByUserId(userId);
    }
}
