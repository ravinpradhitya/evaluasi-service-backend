package com.englishlearning.evaluasi_service.Dto;

import com.englishlearning.evaluasi_service.Entity.ExamQuestion;
import lombok.Data;

@Data
public class ExamQuestionDto {
    private int id;
    private String examQuestion;
    private String examAnswer;
    private int examGrade;
    private int examId;

    public ExamQuestionDto(ExamQuestion examQuestion) {
        this.id = examQuestion.getId();
        this.examQuestion = examQuestion.getExamQuestion();
        this.examAnswer = examQuestion.getExamAnswer();
        this.examGrade = examQuestion.getExamGrade();
        this.examId = examQuestion.getExam().getId();
    }
}
