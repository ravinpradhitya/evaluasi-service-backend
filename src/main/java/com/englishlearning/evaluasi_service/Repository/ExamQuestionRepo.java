package com.englishlearning.evaluasi_service.Repository;

import com.englishlearning.evaluasi_service.Entity.ExamQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamQuestionRepo extends JpaRepository<ExamQuestion, Integer> {
    List<ExamQuestion> findByExamId(int examId);
}
