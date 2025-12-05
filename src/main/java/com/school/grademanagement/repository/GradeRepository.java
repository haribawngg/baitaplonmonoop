package com.school.grademanagement.repository;

import com.school.grademanagement.entity.Grade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    
    // Tìm điểm theo học sinh
    List<Grade> findByStudentId(Long studentId);
    
    // Tìm điểm theo môn học
    List<Grade> findBySubjectId(Long subjectId);
    
    // Tìm điểm theo học sinh và môn học
    List<Grade> findByStudentIdAndSubjectId(Long studentId, Long subjectId);
    
    // Tìm điểm theo học sinh, môn học và loại điểm
    List<Grade> findByStudentIdAndSubjectIdAndGradeType(Long studentId, Long subjectId, String gradeType);
    
    // Tìm điểm theo học sinh, môn học và học kỳ
    List<Grade> findByStudentIdAndSubjectIdAndSemester(Long studentId, Long subjectId, String semester);
    
    // Tìm điểm theo lớp và môn học
    @Query("SELECT g FROM Grade g WHERE g.student.classEntity.id = :classId AND g.subject.id = :subjectId")
    List<Grade> findByClassIdAndSubjectId(@Param("classId") Long classId, @Param("subjectId") Long subjectId);
    
    // Tìm điểm theo lớp, môn học và học kỳ
    @Query("SELECT g FROM Grade g WHERE g.student.classEntity.id = :classId AND g.subject.id = :subjectId AND g.semester = :semester")
    List<Grade> findByClassIdAndSubjectIdAndSemester(@Param("classId") Long classId, @Param("subjectId") Long subjectId, @Param("semester") String semester);
    
    // Tính điểm trung bình của học sinh theo môn học và học kỳ
    @Query("SELECT AVG(g.score) FROM Grade g WHERE g.student.id = :studentId AND g.subject.id = :subjectId AND g.semester = :semester")
    Optional<Double> calculateAverageByStudentAndSubjectAndSemester(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId, @Param("semester") String semester);
    
    // Tính điểm trung bình của học sinh theo môn học (cả năm)
    @Query("SELECT AVG(g.score) FROM Grade g WHERE g.student.id = :studentId AND g.subject.id = :subjectId")
    Optional<Double> calculateAverageByStudentAndSubject(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId);
    
    // Tính điểm trung bình của học sinh theo học kỳ
    @Query("SELECT AVG(g.score) FROM Grade g WHERE g.student.id = :studentId AND g.semester = :semester")
    Optional<Double> calculateAverageByStudentAndSemester(@Param("studentId") Long studentId, @Param("semester") String semester);
    
    // Tính điểm trung bình của học sinh (cả năm)
    @Query("SELECT AVG(g.score) FROM Grade g WHERE g.student.id = :studentId")
    Optional<Double> calculateAverageByStudent(@Param("studentId") Long studentId);
    
    // Tìm điểm theo khoảng thời gian
    List<Grade> findByExamDateBetween(LocalDate startDate, LocalDate endDate);
    
    // Tìm điểm theo năm học
    List<Grade> findByAcademicYear(String academicYear);
    
    // Tìm điểm theo năm học và học kỳ
    List<Grade> findByAcademicYearAndSemester(String academicYear, String semester);
    
    // Đếm số điểm của học sinh theo môn học
    Long countByStudentIdAndSubjectId(Long studentId, Long subjectId);
    
    // Tìm điểm cao nhất của học sinh theo môn học
    @Query("SELECT MAX(g.score) FROM Grade g WHERE g.student.id = :studentId AND g.subject.id = :subjectId")
    Optional<Double> findMaxScoreByStudentAndSubject(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId);
    
    // Tìm điểm thấp nhất của học sinh theo môn học
    @Query("SELECT MIN(g.score) FROM Grade g WHERE g.student.id = :studentId AND g.subject.id = :subjectId")
    Optional<Double> findMinScoreByStudentAndSubject(@Param("studentId") Long studentId, @Param("subjectId") Long subjectId);
}
