package com.englishlearning.evaluasi_service.Dto;

import lombok.Data;

@Data
public class OurStudentDto {
    private int statusCode;
    private String message;
    private StudentDto ourUser;

    // Getters and Setters
    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public StudentDto getOurUser() {
        return ourUser;
    }

    public void setOurUser(StudentDto ourUser) {
        this.ourUser = ourUser;
    }
}