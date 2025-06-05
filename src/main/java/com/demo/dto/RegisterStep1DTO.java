package com.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterStep1DTO {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "NIM is required")
    private String nim;

    @NotNull(message = "Year of entry is required")
    private Integer yearOfEntry;

    @NotBlank(message = "Study program is required")
    private String studyProgram;

    @NotBlank(message = "Faculty is required")
    private String faculty;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Whatsapp number is required")
    private String whatsapp;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @NotBlank(message = "Address is required")
    private String address;

    // Getters and setters

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
}