package com.englishlearning.evaluasi_service.Dto;

import java.util.List;

public class ReqRes {
    private int statusCode;
    private String message;
    private List<StudentDto> ourUserList;

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

    public List<StudentDto> getOurUserList() {
        return ourUserList;
    }

    public void setOurUserList(List<StudentDto> ourUserList) {
        this.ourUserList = ourUserList;
    }
}
