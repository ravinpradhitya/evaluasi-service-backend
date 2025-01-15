package com.englishlearning.evaluasi_service.Dto;

import com.englishlearning.evaluasi_service.Entity.Answer;
import lombok.Data;

@Data
public class AnswerResponse {
    private int id;
    private String answer;
    private Boolean isCorrect;
    private Integer marksObtained;
    private ExamQuestionDto examQuestion;
    private StudentDto student;

    public AnswerResponse(Answer answer, ExamQuestionDto examQuestionDto, StudentDto studentDto) {
        this.id = answer.getId();
        this.answer = answer.getAnswer();
        this.isCorrect = answer.getIsCorrect();
        this.marksObtained = answer.getMarksObtained();
        this.examQuestion = examQuestionDto;
        this.student = studentDto;
    }
}
