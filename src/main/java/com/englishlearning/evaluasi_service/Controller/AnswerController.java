package com.englishlearning.evaluasi_service.Controller;

import com.englishlearning.evaluasi_service.Dto.AnswerResponse;
import com.englishlearning.evaluasi_service.Dto.ExamQuestionDto;
import com.englishlearning.evaluasi_service.Dto.StudentDto;
import com.englishlearning.evaluasi_service.Entity.Answer;
import com.englishlearning.evaluasi_service.Service.AnswerService;
import com.englishlearning.evaluasi_service.Service.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AnswerController {

    @Autowired
    private AnswerService answerService;

    @Autowired
    private UserServiceClient userServiceClient;

    @CrossOrigin
    @PostMapping("/addAnswer")
    public Answer addAnswer(@RequestBody Answer answer) {
        return answerService.saveAnswer(answer);
    }

    @CrossOrigin
    @GetMapping("/question/{questionId}")
    public List<AnswerResponse> getAnswersByQuestionId(@PathVariable int questionId) {
        List<Answer> answers = answerService.getAnswersByQuestionId(questionId);

        return answers.stream().map(answer -> {

            ExamQuestionDto examQuestionDto = new ExamQuestionDto(answer.getExamQuestion());

            StudentDto student = null;
            try {
                student = userServiceClient.getStudentById(answer.getStudentId());
            } catch (Exception e) {
                System.err.println("Error fetching student data: " + e.getMessage());
            }

            return new AnswerResponse(answer, examQuestionDto, student);
        }).collect(Collectors.toList());
    }

    @CrossOrigin
    @GetMapping("/getAllAnswers")
    public List<Answer> getAllAnswers() { return answerService.getAllAnswers(); }

    @CrossOrigin
    @PutMapping("/grade/{id}")
    public Answer answer(
            @PathVariable int id,
            @RequestParam Boolean isCorrect,
            @RequestParam Integer marks) {
        return answerService.gradeAnswer(id, isCorrect, marks);
    }

    @CrossOrigin
    @DeleteMapping("/delete/{id}")
    public String deleteStudentAnswer(@PathVariable int id) {
        return answerService.deleteAnswer(id);
    }
}
