package com.school.grademanagement.repository;

import com.school.grademanagement.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<ClassEntity, Long> {
    
    // Tìm lớp theo tên lớp
    Optional<ClassEntity> findByClassName(String className);
    
    // Tìm lớp theo khối
    List<ClassEntity> findByGradeLevel(String gradeLevel);
    
    // Tìm lớp theo năm học
    List<ClassEntity> findByAcademicYear(String academicYear);
    
    // Tìm lớp theo khối và năm học
    List<ClassEntity> findByGradeLevelAndAcademicYear(String gradeLevel, String academicYear);
    
    // Tìm lớp đang hoạt động
    @Query("SELECT c FROM ClassEntity c WHERE c.id IN (SELECT DISTINCT s.classEntity.id FROM Student s WHERE s.isActive = true)")
    List<ClassEntity> findActiveClasses();
    
    // Đếm số học sinh trong lớp
    @Query("SELECT COUNT(s) FROM Student s WHERE s.classEntity.id = :classId AND s.isActive = true")
    Long countStudentsInClass(@Param("classId") Long classId);
    
    // Tìm lớp theo giáo viên chủ nhiệm
    @Query("SELECT c FROM ClassEntity c WHERE c.homeroomTeacher.id = :teacherId")
    Optional<ClassEntity> findByHomeroomTeacherId(@Param("teacherId") Long teacherId);
}
