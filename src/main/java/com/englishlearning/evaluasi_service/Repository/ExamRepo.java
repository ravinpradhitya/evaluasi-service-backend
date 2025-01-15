package com.englishlearning.evaluasi_service.Repository;

import com.englishlearning.evaluasi_service.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepo extends JpaRepository<Exam, Integer> {
}
