package com.englishlearning.evaluasi_service.Service;

import com.englishlearning.evaluasi_service.Entity.Answer;
import com.englishlearning.evaluasi_service.Repository.AnswerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {

    @Autowired
    private AnswerRepo answerRepo;

    public Answer saveAnswer(Answer answer) { return answerRepo.save(answer); }

    public List<Answer> getAnswersByQuestionId(int questionId) {
        return answerRepo.findByExamQuestionId(questionId);
    }

    public List<Answer> getAllAnswers() {
        return answerRepo.findAll();
    }

    public Answer gradeAnswer(int id, Boolean isCorrect, Integer marks){
        Answer answer = answerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Answer not found"));
        answer.setIsCorrect(isCorrect);
        answer.setMarksObtained(marks);
        return answerRepo.save(answer);
    }

    public String deleteAnswer(int id) {
        if (answerRepo.existsById(id)){
            answerRepo.deleteById(id);
            return "Deleted answer with ID: "+id;
        }else {
            return "Answer with ID: " + id + " does not exist.";
        }
    }
}
