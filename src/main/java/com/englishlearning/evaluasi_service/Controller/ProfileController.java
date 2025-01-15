package com.englishlearning.evaluasi_service.Controller;

import com.englishlearning.evaluasi_service.Dto.StudentDto;
import com.englishlearning.evaluasi_service.Service.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private UserServiceClient userServiceClient;

    @GetMapping("/{studentId}")
    public StudentDto getUserProfile(@PathVariable String studentId) {
        return userServiceClient.getStudentById(studentId);
    }
}
