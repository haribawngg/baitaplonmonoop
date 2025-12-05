package com.school.grademanagement.repository;

import com.school.grademanagement.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    
    // Tìm giáo viên theo mã giáo viên
    Optional<Teacher> findByTeacherCode(String teacherCode);
    
    // Tìm giáo viên đang hoạt động
    List<Teacher> findByIsActiveTrue();
    
    // Tìm giáo viên theo mã giáo viên và đang hoạt động
    Optional<Teacher> findByTeacherCodeAndIsActiveTrue(String teacherCode);
    
    // Tìm giáo viên theo tên (tìm kiếm gần đúng)
    @Query("SELECT t FROM Teacher t WHERE t.fullName LIKE %:name% AND t.isActive = true")
    List<Teacher> findByFullNameContaining(@Param("name") String name);
    
    // Tìm giáo viên theo chuyên môn
    @Query("SELECT t FROM Teacher t WHERE t.specialization LIKE %:specialization% AND t.isActive = true")
    List<Teacher> findBySpecializationContaining(@Param("specialization") String specialization);
    
    // Tìm giáo viên chủ nhiệm
    @Query("SELECT t FROM Teacher t WHERE t.classEntity IS NOT NULL AND t.isActive = true")
    List<Teacher> findHomeroomTeachers();
    
    // Tìm giáo viên không phải chủ nhiệm
    @Query("SELECT t FROM Teacher t WHERE t.classEntity IS NULL AND t.isActive = true")
    List<Teacher> findNonHomeroomTeachers();
    
    // Đếm số giáo viên đang hoạt động
    Long countByIsActiveTrue();
}
