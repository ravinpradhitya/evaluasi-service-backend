package com.englishlearning.evaluasi_service.Controller;

import com.englishlearning.evaluasi_service.Entity.Exam;
import com.englishlearning.evaluasi_service.Service.ExamService;
import com.englishlearning.evaluasi_service.Service.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private UserServiceClient userServiceClient;

    @PostMapping("/addExam")
    public Exam postDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                            @RequestBody Exam exam) {
        String token = validateToken(authorizationHeader);
        try {
            userServiceClient.getTeacherProfile(token); // Verify teacher role
        } catch (ResponseStatusException e) {
            // Handle the exception and return the appropriate status
            if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not authorized as a teacher");
            } else {
                throw e; // Re-throw other exceptions
            }
        }
        return examService.saveDetails(exam);
    }

    @PostMapping("/addExamList")
    public List<Exam> postDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                  @RequestBody List<Exam> exams) {
        String token = validateToken(authorizationHeader);
        try {
            userServiceClient.getTeacherProfile(token); // Verify teacher role
        } catch (ResponseStatusException e) {
            if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not authorized as a teacher");
            } else {
                throw e; // Re-throw other exceptions
            }
        }
        return examService.saveListDetails(exams);
    }

    @GetMapping("/getExamById/{id}")
    public Exam fetchDetailsById(@PathVariable int id){
        return examService.getExamDetailsById(id);
    }

    @GetMapping("/getAllExams")
    public List<Exam> getDetails()
    {
        return examService.getAllDetails();
    }

    @PutMapping("/updateExam")
    public Exam updateExamDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                  @RequestBody Exam exam) {
        String token = validateToken(authorizationHeader);
        try {
            userServiceClient.getTeacherProfile(token); // Verify teacher role
        } catch (ResponseStatusException e) {
            if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not authorized as a teacher");
            } else {
                throw e; // Re-throw other exceptions
            }
        }
        return examService.updateDetail(exam);
    }

    @DeleteMapping("/deleteExam/{id}")
    public String deleteFunction(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                 @PathVariable int id) {
        String token = validateToken(authorizationHeader);
        try {
            userServiceClient.getTeacherProfile(token); // Verify teacher role
        } catch (ResponseStatusException e) {
            if (e.getStatusCode() == HttpStatus.FORBIDDEN) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not authorized as a teacher");
            } else {
                throw e; // Re-throw other exceptions
            }
        }
        return examService.deleteExam(id);
    }

    private String validateToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization header");
        }
        return authorizationHeader.substring(7);
    }
}
