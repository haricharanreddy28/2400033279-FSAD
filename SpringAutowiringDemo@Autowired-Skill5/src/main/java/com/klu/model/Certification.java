package com.klu.model;

import org.springframework.stereotype.Component;

@Component
public class Certification {

    private int id = 33356;
    private String name = "FSAD";
    private String dateOfCompletion = "28-APRIL-2026";

    @Override
    public String toString() {
        return "Certification [id=" + id +
                ", name=" + name +
                ", dateOfCompletion=" + dateOfCompletion + "]";
    }
}