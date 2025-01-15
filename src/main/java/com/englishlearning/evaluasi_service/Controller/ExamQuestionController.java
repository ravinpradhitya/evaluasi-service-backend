package com.englishlearning.evaluasi_service.Controller;

import com.englishlearning.evaluasi_service.Entity.ExamQuestion;
import com.englishlearning.evaluasi_service.Service.ExamQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExamQuestionController {

    @Autowired
    private ExamQuestionService examquestionService;

    @CrossOrigin
    @PostMapping("/addExamQuestion")
    public ExamQuestion postDetails(@RequestBody ExamQuestion examquestion)
    {
        return examquestionService.saveDetails(examquestion);
    }

    @CrossOrigin
    @GetMapping("/getQuestionsByExamId/{examId}")
    public List<ExamQuestion> getQuestionsByExamId(@PathVariable int examId) {
        return examquestionService.getQuestionsByExamId(examId);
    }

    @CrossOrigin
    @GetMapping("/getExamQuestionById/{id}")
    public ExamQuestion fetchDetailsById(@PathVariable int id){
        return examquestionService.getExamQuestionDetailsById(id);
    }

    @CrossOrigin
    @GetMapping("/getAllExamQuestions")
    public List<ExamQuestion> getDetails()
    {
        return examquestionService.getAllDetails();
    }

    @CrossOrigin
    @PutMapping("/updateExamQuestion")
    public ExamQuestion updateExamQuestionDetails(@RequestBody ExamQuestion examquestion)
    {
        return examquestionService.updateDetail(examquestion);
    }

    @CrossOrigin
    @DeleteMapping("/deleteExamQuestion/{id}")
    public String deleteFunction(@PathVariable int id)
    {
        return examquestionService.deleteExamQuestion(id);
    }

    @CrossOrigin
    @PostMapping("/addExamQuestionList")
    public List<ExamQuestion> postDetails(@RequestBody List<ExamQuestion> examQuestions)
    {
        return examquestionService.saveListDetails(examQuestions);
    }
}
