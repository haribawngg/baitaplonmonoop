package com.school.grademanagement.repository;

import com.school.grademanagement.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    
    // Tìm môn học theo mã môn học
    Optional<Subject> findBySubjectCode(String subjectCode);
    
    // Tìm môn học đang hoạt động
    List<Subject> findByIsActiveTrue();
    
    // Tìm môn học theo mã môn học và đang hoạt động
    Optional<Subject> findBySubjectCodeAndIsActiveTrue(String subjectCode);
    
    // Tìm môn học theo tên (tìm kiếm gần đúng)
    @Query("SELECT s FROM Subject s WHERE s.subjectName LIKE %:name% AND s.isActive = true")
    List<Subject> findBySubjectNameContaining(@Param("name") String name);
    
    // Tìm môn học theo khối
    List<Subject> findByGradeLevelAndIsActiveTrue(String gradeLevel);
    
    // Tìm môn học theo giáo viên
    @Query("SELECT s FROM Subject s WHERE s.teacher.id = :teacherId AND s.isActive = true")
    List<Subject> findByTeacherId(@Param("teacherId") Long teacherId);
    
    // Tìm môn học theo giáo viên và khối
    @Query("SELECT s FROM Subject s WHERE s.teacher.id = :teacherId AND s.gradeLevel = :gradeLevel AND s.isActive = true")
    List<Subject> findByTeacherIdAndGradeLevel(@Param("teacherId") Long teacherId, @Param("gradeLevel") String gradeLevel);
    
    // Đếm số môn học đang hoạt động
    Long countByIsActiveTrue();
    
    // Đếm số môn học theo khối
    Long countByGradeLevelAndIsActiveTrue(String gradeLevel);
}
