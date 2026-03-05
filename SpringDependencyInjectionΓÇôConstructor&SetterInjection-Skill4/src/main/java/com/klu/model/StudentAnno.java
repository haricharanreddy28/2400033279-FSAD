package com.klu.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class StudentAnno {

    private int studentId;
    private String name;
    private String course;
    private int year;

    // Constructor Injection using @Value
    public StudentAnno(
            @Value("33356") int studentId,
            @Value("Gourav") String name,
            @Value("FSAD") String course,
            @Value("2026") int year) {

        this.studentId = studentId;
        this.name = name;
        this.course = course;
        this.year = year;
    }

    // Setter Injection
    @Value("Advanced Spring")
    public void setCourse(String course) {
        this.course = course;
    }

    @Value("2025")
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "StudentAnno {" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", course='" + course + '\'' +
                ", year=" + year +
                '}';
    }
}
