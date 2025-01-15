package com.englishlearning.evaluasi_service.Repository;

import com.englishlearning.evaluasi_service.Entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepo extends JpaRepository<Answer, Integer> {
    List<Answer> findByExamQuestionId(int questionId);
}
