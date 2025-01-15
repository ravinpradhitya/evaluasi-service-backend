package com.englishlearning.evaluasi_service.Service;

import com.englishlearning.evaluasi_service.Entity.Exam;
import com.englishlearning.evaluasi_service.Repository.ExamRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamService {

    @Autowired
    private ExamRepo examRepo;

    public Exam saveDetails(Exam exam){
        return examRepo.save(exam);
    }

    public Exam getExamDetailsById(int id)
    {
        return examRepo.findById(id).orElse(null);
    }

    public List<Exam> getAllDetails()
    {
        return examRepo.findAll();
    }

    public Exam updateDetail(Exam exam){

        Exam updateExam=examRepo.findById(exam.getId()).orElse(null);
        if(updateExam !=null)
        {
            updateExam.setExamName(exam.getExamName());
            updateExam.setDescription(exam.getDescription());
            updateExam.setDate(exam.getDate());
            updateExam.setDuration(exam.getDuration());
            updateExam.setTotalMarks(exam.getTotalMarks());
            updateExam.setPassMarks(exam.getPassMarks());
            examRepo.save(updateExam);
            return updateExam;
        }
        return null;
    }

    public String deleteExam(int id){
        if(examRepo.existsById(id)){
            examRepo.deleteById(id);
            return "Deleted exam with ID: "+id;
        }else {
            return "Exam with ID: " + id + " does not exist.";
        }
    }

    public List<Exam> saveListDetails(List<Exam> exams)
    {
        return examRepo.saveAll(exams);
    }
}
