package com.englishlearning.evaluasi_service.Service;

import com.englishlearning.evaluasi_service.Entity.ExamQuestion;
import com.englishlearning.evaluasi_service.Repository.ExamQuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamQuestionService {
    @Autowired
    private ExamQuestionRepo examquestionRepo;

    @Autowired
    private ExamService examService;

    public List<ExamQuestion> getQuestionsByExamId(int examId) {
        return examquestionRepo.findByExamId(examId);
    }

    public ExamQuestion saveDetails(ExamQuestion examquestion){
        return examquestionRepo.save(examquestion);
    }

    public ExamQuestion getExamQuestionDetailsById(int id)
    {
        return examquestionRepo.findById(id).orElse(null);
    }

    public List<ExamQuestion> getAllDetails()
    {
        return examquestionRepo.findAll();
    }

    public ExamQuestion updateDetail(ExamQuestion examquestion){

        ExamQuestion updateExamQuestion=examquestionRepo.findById(examquestion.getId()).orElse(null);
        if(updateExamQuestion !=null)
        {
            updateExamQuestion.setExamQuestion(examquestion.getExamQuestion());
            updateExamQuestion.setExamAnswer(examquestion.getExamAnswer());
            updateExamQuestion.setExamGrade(examquestion.getExamGrade());
            examquestionRepo.save(updateExamQuestion);
            return updateExamQuestion;
        }
        return null;
    }

    public String deleteExamQuestion(int id){
        if(examquestionRepo.existsById(id)){
            examquestionRepo.deleteById(id);
            return "Deleted exam question with ID: " + id;
        }else {
            return "Exam question with ID: " + id + " does not exist.";
        }
    }

    public List<ExamQuestion> saveListDetails(List<ExamQuestion> examQuestions)
    {
        return examquestionRepo.saveAll(examQuestions);
    }
}
