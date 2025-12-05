package com.school.grademanagement.repository;

import com.school.grademanagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
    // Tìm học sinh theo mã học sinh
    Optional<Student> findByStudentCode(String studentCode);
    
    // Tìm học sinh theo lớp
    List<Student> findByClassEntityId(Long classId);
    
    // Tìm học sinh đang hoạt động theo lớp
    List<Student> findByClassEntityIdAndIsActiveTrue(Long classId);
    
    // Tìm học sinh theo tên (tìm kiếm gần đúng)
    @Query("SELECT s FROM Student s WHERE s.fullName LIKE %:name% AND s.isActive = true")
    List<Student> findByFullNameContaining(@Param("name") String name);
    
    // Tìm học sinh theo mã học sinh và đang hoạt động
    Optional<Student> findByStudentCodeAndIsActiveTrue(String studentCode);
    
    // Tìm học sinh theo lớp và đang hoạt động
    @Query("SELECT s FROM Student s WHERE s.classEntity.id = :classId AND s.isActive = true ORDER BY s.fullName")
    List<Student> findActiveStudentsByClass(@Param("classId") Long classId);
    
    // Đếm số học sinh trong lớp
    Long countByClassEntityIdAndIsActiveTrue(Long classId);
    
    // Tìm học sinh theo năm học
    @Query("SELECT s FROM Student s WHERE s.classEntity.academicYear = :academicYear AND s.isActive = true")
    List<Student> findByAcademicYear(@Param("academicYear") String academicYear);
    
    // Tìm học sinh theo khối
    @Query("SELECT s FROM Student s WHERE s.classEntity.gradeLevel = :gradeLevel AND s.isActive = true")
    List<Student> findByGradeLevel(@Param("gradeLevel") String gradeLevel);
}
