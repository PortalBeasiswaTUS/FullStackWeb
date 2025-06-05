package com.demo.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class RegisterStep2DTO {

    @NotNull(message = "Semester 1 GPA is required")
    @DecimalMin(value = "0.00", inclusive = true, message = "GPA must be at least 0")
    @DecimalMax(value = "4.00", inclusive = true, message = "GPA must be at most 4")
    private BigDecimal semester1;

    @DecimalMin(value = "0.00", inclusive = true)
    @DecimalMax(value = "4.00", inclusive = true)
    private BigDecimal semester2;

    @DecimalMin(value = "0.00", inclusive = true)
    @DecimalMax(value = "4.00", inclusive = true)
    private BigDecimal semester3;

    @DecimalMin(value = "0.00", inclusive = true)
    @DecimalMax(value = "4.00", inclusive = true)
    private BigDecimal semester4;

    @DecimalMin(value = "0.00", inclusive = true)
    @DecimalMax(value = "4.00", inclusive = true)
    private BigDecimal semester5;

    @DecimalMin(value = "0.00", inclusive = true)
    @DecimalMax(value = "4.00", inclusive = true)
    private BigDecimal semester6;

    @DecimalMin(value = "0.00", inclusive = true)
    @DecimalMax(value = "4.00", inclusive = true)
    private BigDecimal semester7;

    @DecimalMin(value = "0.00", inclusive = true)
    @DecimalMax(value = "4.00", inclusive = true)
    private BigDecimal semester8;

    // Getters and Setters

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
}