package com.englishlearning.evaluasi_service.Controller;

import com.englishlearning.evaluasi_service.Entity.Exam;
import com.englishlearning.evaluasi_service.Service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExamController {

    @Autowired
    private ExamService examService;

    @CrossOrigin
    @PostMapping("/addExam")
    public Exam postDetails(@RequestBody Exam exam)
    {
        return examService.saveDetails(exam);
    }

    @CrossOrigin
    @GetMapping("/getExamById/{id}")
    public Exam fetchDetailsById(@PathVariable int id){
        return examService.getExamDetailsById(id);
    }

    @CrossOrigin
    @GetMapping("/getAllExams")
    public List<Exam> getDetails()
    {
        return examService.getAllDetails();
    }

    @CrossOrigin
    @PutMapping("/updateExam")
    public Exam updateExamDetails(@RequestBody Exam exam)
    {
        return examService.updateDetail(exam);
    }

    @CrossOrigin
    @DeleteMapping("/deleteExam/{id}")
    public String deleteFunction(@PathVariable int id)
    {
        return examService.deleteExam(id);
    }

    @CrossOrigin
    @PostMapping("/addExamList")
    public List<Exam> postDetails(@RequestBody List<Exam> exams)
    {
        return examService.saveListDetails(exams);
    }
}
