package com.englishlearning.evaluasi_service.Controller;

import com.englishlearning.evaluasi_service.Entity.ExamQuestion;
import com.englishlearning.evaluasi_service.Service.ExamQuestionService;
import com.englishlearning.evaluasi_service.Service.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin
@RestController
public class ExamQuestionController {

    @Autowired
    private ExamQuestionService examquestionService;

    @Autowired
    private UserServiceClient userServiceClient;

    @PostMapping("/addExamQuestion")
    public ExamQuestion postDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                    @RequestBody ExamQuestion examquestion) {
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
        return examquestionService.saveDetails(examquestion);
    }

    @GetMapping("/getQuestionsByExamId/{examId}")
    public List<ExamQuestion> getQuestionsByExamId(@PathVariable("examId") int examId) {
        return examquestionService.getQuestionsByExamId(examId);
    }

    @GetMapping("/getExamQuestionById/{id}")
    public ExamQuestion fetchDetailsById(@PathVariable("id") int id) {
        return examquestionService.getExamQuestionDetailsById(id);
    }

    @GetMapping("/getAllExamQuestions")
    public List<ExamQuestion> getDetails() {
        return examquestionService.getAllDetails();
    }

    @PutMapping("/updateExamQuestion")
    public ExamQuestion updateExamQuestionDetails(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
                                                  @RequestBody ExamQuestion examquestion) {
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
        return examquestionService.updateDetail(examquestion);
    }

    @DeleteMapping("/deleteExamQuestion/{id}")
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
        return examquestionService.deleteExamQuestion(id);
    }

    @PostMapping("/addExamQuestionList")
    public List<ExamQuestion> postDetails(@RequestBody List<ExamQuestion> examQuestions) {
        return examquestionService.saveListDetails(examQuestions);
    }

    private String validateToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization header");
        }
        return authorizationHeader.substring(7);
    }

}
