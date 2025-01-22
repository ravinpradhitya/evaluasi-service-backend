package com.englishlearning.evaluasi_service.Dto;

import lombok.Data;

@Data
public class OurTeacherDto {
    private int statusCode;
    private String message;
    private TeacherDto ourUser; // This will hold the teacher's details
}
