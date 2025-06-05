package com.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "nim", nullable = false, unique = true, length = 20)
    private String nim;

    @Column(name = "year_of_entry", nullable = false)
    private Integer yearOfEntry;

    @Column(name = "study_program", length = 100)
    private String studyProgram;

    @Column(name = "faculty", length = 100)
    private String faculty;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "whatsapp", length = 20)
    private String whatsapp;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "semester1", precision = 3, scale = 2)
    private BigDecimal semester1;

    @Column(name = "semester2", precision = 3, scale = 2)
    private BigDecimal semester2;

    @Column(name = "semester3", precision = 3, scale = 2)
    private BigDecimal semester3;

    @Column(name = "semester4", precision = 3, scale = 2)
    private BigDecimal semester4;

    @Column(name = "semester5", precision = 3, scale = 2)
    private BigDecimal semester5;

    @Column(name = "semester6", precision = 3, scale = 2)
    private BigDecimal semester6;

    @Column(name = "semester7", precision = 3, scale = 2)
    private BigDecimal semester7;

    @Column(name = "semester8", precision = 3, scale = 2)
    private BigDecimal semester8;

    @Column(name = "gpa", precision = 3, scale = 2)
    private BigDecimal gpa;

    @Column(name = "transcript_path", length = 255)
    private String transcriptPath;

    @Column(name = "profile_user", length = 255)
    private String profileUser;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public enum Role {
        USER, ADMIN
    }

    // Getter dan Setter...
    // (Sama seperti yang sudah Anda buat)


    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public Integer getYearOfEntry() {
        return yearOfEntry;
    }

    public void setYearOfEntry(Integer yearOfEntry) {
        this.yearOfEntry = yearOfEntry;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhatsapp() {
        return whatsapp;
    }

    public void setWhatsapp(String whatsapp) {
        this.whatsapp = whatsapp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getSemester1() {
        return semester1;
    }

    public void setSemester1(BigDecimal semester1) {
        this.semester1 = semester1;
    }

    public BigDecimal getSemester2() {
        return semester2;
    }

    public void setSemester2(BigDecimal semester2) {
        this.semester2 = semester2;
    }

    public BigDecimal getSemester3() {
        return semester3;
    }

    public void setSemester3(BigDecimal semester3) {
        this.semester3 = semester3;
    }

    public BigDecimal getSemester4() {
        return semester4;
    }

    public void setSemester4(BigDecimal semester4) {
        this.semester4 = semester4;
    }

    public BigDecimal getSemester5() {
        return semester5;
    }

    public void setSemester5(BigDecimal semester5) {
        this.semester5 = semester5;
    }

    public BigDecimal getSemester6() {
        return semester6;
    }

    public void setSemester6(BigDecimal semester6) {
        this.semester6 = semester6;
    }

    public BigDecimal getSemester7() {
        return semester7;
    }

    public void setSemester7(BigDecimal semester7) {
        this.semester7 = semester7;
    }

    public BigDecimal getSemester8() {
        return semester8;
    }

    public void setSemester8(BigDecimal semester8) {
        this.semester8 = semester8;
    }

    public BigDecimal getGpa() {
        return gpa;
    }

    public void setGpa(BigDecimal gpa) {
        this.gpa = gpa;
    }

    public String getTranscriptPath() {
        return transcriptPath;
    }

    public void setTranscriptPath(String transcriptPath) {
        this.transcriptPath = transcriptPath;
    }

    public String getProfileUser() {
        return profileUser;
    }

    public void setProfileUser(String profileUser) {
        this.profileUser = profileUser;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}