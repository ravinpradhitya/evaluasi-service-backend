package com.englishlearning.evaluasi_service.Controller;

import com.englishlearning.evaluasi_service.Dto.AnswerResponse;
import com.englishlearning.evaluasi_service.Dto.ExamQuestionDto;
import com.englishlearning.evaluasi_service.Dto.StudentDto;
import com.englishlearning.evaluasi_service.Entity.Answer;
import com.englishlearning.evaluasi_service.Service.AnswerService;
import com.englishlearning.evaluasi_service.Service.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserServiceClient userServiceClient;

    @PostMapping("/addAnswer")
    public Answer addAnswer(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @RequestBody Answer answer) {

        String token = authorizationHeader.substring(7);

        return answerService.saveAnswer(token, answer);
    }

//    @GetMapping("/question/{questionId}")
//    public List<AnswerResponse> getAnswersByQuestionId(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader, @PathVariable int questionId) {
//        List<Answer> answers = answerService.getAnswersByQuestionId(questionId);
//
//        return answers.stream().map(answer -> {
//
//            ExamQuestionDto examQuestionDto = new ExamQuestionDto(answer.getExamQuestion());
//
//            StudentDto student = null;
//
//            String token = authorizationHeader.substring(7);
//
//            try {
//                student = userServiceClient.getStudentId(token, answer.getStudentId());
//            } catch (Exception e) {
//                System.err.println("Error fetching student data for Student ID: " + answer.getStudentId() + " - " + e.getMessage());
//            }
//
//            return new AnswerResponse(answer, examQuestionDto, student);
//        }).collect(Collectors.toList());
//    }

    @GetMapping("/question/{questionId}")
    public List<Answer> getAnswersByQuestionId(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
            @PathVariable int questionId) {
        // Validate the token
        String token = validateToken(authorizationHeader);
        userServiceClient.getTeacherProfile(token);

        // Fetch all answers for the specific question ID and return them directly
        return answerService.getAnswersByQuestionId(questionId);
    }

    private String validateToken(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing or invalid Authorization header");
        }
        return authorizationHeader.substring(7);
    }

    @GetMapping("/getAllAnswers")
    public List<Answer> getAllAnswers() { return answerService.getAllAnswers(); }

    @PutMapping("/grade/{id}")
    public Answer answer(
            @PathVariable int id,
            @RequestParam Boolean isCorrect,
            @RequestParam Integer marks) {
        return answerService.gradeAnswer(id, isCorrect, marks);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentAnswer(@PathVariable int id) {
        return answerService.deleteAnswer(id);
    }
}
