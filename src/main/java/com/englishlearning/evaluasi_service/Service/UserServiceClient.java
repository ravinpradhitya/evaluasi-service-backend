package com.englishlearning.evaluasi_service.Service;

import com.englishlearning.evaluasi_service.Dto.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String USER_SERVICE_BASE_URL = "http://localhost:8080";

    public StudentDto getStudentById(String studentId) {
        String url = USER_SERVICE_BASE_URL + "/users/" + studentId;
        return restTemplate.getForObject(url, StudentDto.class);
    }
}
