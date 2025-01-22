package com.englishlearning.evaluasi_service.Controller;

import com.englishlearning.evaluasi_service.Dto.StudentDto;
import com.englishlearning.evaluasi_service.Service.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class ProfileController {

    @Autowired
    private UserServiceClient userServiceClient;

    @GetMapping("/profile")
    public StudentDto getUserProfile(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        // Check if the Authorization header is present and starts with "Bearer "
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization header");
        }

        // Extract the token
        String token = authorizationHeader.substring(7);

        // Pass the token to the service and return the result
        return userServiceClient.getUserProfile(token);
    }

    @GetMapping("/teacher/students")
    public List<StudentDto> getStudents(@RequestHeader("Authorization") String token) {
        return userServiceClient.getStudentList(token.substring(7));
    }
}
