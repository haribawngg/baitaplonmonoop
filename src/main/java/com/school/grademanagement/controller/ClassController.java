package com.school.grademanagement.controller;

import com.school.grademanagement.entity.ClassEntity;
import com.school.grademanagement.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "*")
public class ClassController {
    
    @Autowired
    private ClassService classService;
    
    // Lấy tất cả lớp học
    @GetMapping
    public ResponseEntity<List<ClassEntity>> getAllClasses() {
        List<ClassEntity> classes = classService.getAllClasses();
        return ResponseEntity.ok(classes);
    }
    
    // Lấy lớp học theo ID
    @GetMapping("/{id}")
    public ResponseEntity<ClassEntity> getClassById(@PathVariable Long id) {
        Optional<ClassEntity> classEntity = classService.getClassById(id);
        return classEntity.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Lấy lớp học theo tên lớp
    @GetMapping("/name/{className}")
    public ResponseEntity<ClassEntity> getClassByName(@PathVariable String className) {
        Optional<ClassEntity> classEntity = classService.getClassByName(className);
        return classEntity.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Lấy lớp học theo khối
    @GetMapping("/grade-level/{gradeLevel}")
    public ResponseEntity<List<ClassEntity>> getClassesByGradeLevel(@PathVariable String gradeLevel) {
        List<ClassEntity> classes = classService.getClassesByGradeLevel(gradeLevel);
        return ResponseEntity.ok(classes);
    }
    
    // Lấy lớp học theo năm học
    @GetMapping("/academic-year/{academicYear}")
    public ResponseEntity<List<ClassEntity>> getClassesByAcademicYear(@PathVariable String academicYear) {
        List<ClassEntity> classes = classService.getClassesByAcademicYear(academicYear);
        return ResponseEntity.ok(classes);
    }
    
    // Lấy lớp học theo khối và năm học
    @GetMapping("/grade-level/{gradeLevel}/academic-year/{academicYear}")
    public ResponseEntity<List<ClassEntity>> getClassesByGradeLevelAndAcademicYear(
            @PathVariable String gradeLevel, 
            @PathVariable String academicYear) {
        List<ClassEntity> classes = classService.getClassesByGradeLevelAndAcademicYear(gradeLevel, academicYear);
        return ResponseEntity.ok(classes);
    }
    
    // Lấy các lớp đang hoạt động
    @GetMapping("/active")
    public ResponseEntity<List<ClassEntity>> getActiveClasses() {
        List<ClassEntity> classes = classService.getActiveClasses();
        return ResponseEntity.ok(classes);
    }
    
    // Tạo lớp học mới
    @PostMapping
    public ResponseEntity<ClassEntity> createClass(@Valid @RequestBody ClassEntity classEntity) {
        if (classService.existsByClassName(classEntity.getClassName())) {
            return ResponseEntity.badRequest().build();
        }
        ClassEntity savedClass = classService.saveClass(classEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClass);
    }
    
    // Cập nhật lớp học
    @PutMapping("/{id}")
    public ResponseEntity<ClassEntity> updateClass(@PathVariable Long id, @Valid @RequestBody ClassEntity classEntityDetails) {
        if (!classService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        // Kiểm tra tên lớp trùng lặp (trừ lớp hiện tại)
        Optional<ClassEntity> existingClass = classService.getClassByName(classEntityDetails.getClassName());
        if (existingClass.isPresent() && !existingClass.get().getId().equals(id)) {
            return ResponseEntity.badRequest().build();
        }
        
        ClassEntity updatedClass = classService.updateClass(id, classEntityDetails);
        return updatedClass != null ? ResponseEntity.ok(updatedClass) : ResponseEntity.notFound().build();
    }
    
    // Xóa lớp học
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        if (!classService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        boolean deleted = classService.deleteClass(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    // Đếm số học sinh trong lớp
    @GetMapping("/{id}/student-count")
    public ResponseEntity<Long> getStudentCountInClass(@PathVariable Long id) {
        if (!classService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        Long count = classService.countStudentsInClass(id);
        return ResponseEntity.ok(count);
    }
    
    // Lấy lớp học theo giáo viên chủ nhiệm
    @GetMapping("/homeroom-teacher/{teacherId}")
    public ResponseEntity<ClassEntity> getClassByHomeroomTeacher(@PathVariable Long teacherId) {
        Optional<ClassEntity> classEntity = classService.getClassByHomeroomTeacher(teacherId);
        return classEntity.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
