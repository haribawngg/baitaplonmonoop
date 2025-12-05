package com.school.grademanagement.controller;

import com.school.grademanagement.entity.Student;
import com.school.grademanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    // Lấy tất cả học sinh
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
    
    // Lấy học sinh theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Lấy học sinh theo mã học sinh
    @GetMapping("/code/{studentCode}")
    public ResponseEntity<Student> getStudentByCode(@PathVariable String studentCode) {
        Optional<Student> student = studentService.getStudentByCode(studentCode);
        return student.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Lấy học sinh đang hoạt động theo mã học sinh
    @GetMapping("/code/{studentCode}/active")
    public ResponseEntity<Student> getActiveStudentByCode(@PathVariable String studentCode) {
        Optional<Student> student = studentService.getActiveStudentByCode(studentCode);
        return student.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Lấy học sinh theo lớp
    @GetMapping("/class/{classId}")
    public ResponseEntity<List<Student>> getStudentsByClass(@PathVariable Long classId) {
        List<Student> students = studentService.getStudentsByClass(classId);
        return ResponseEntity.ok(students);
    }
    
    // Lấy học sinh đang hoạt động theo lớp
    @GetMapping("/class/{classId}/active")
    public ResponseEntity<List<Student>> getActiveStudentsByClass(@PathVariable Long classId) {
        List<Student> students = studentService.getActiveStudentsByClass(classId);
        return ResponseEntity.ok(students);
    }
    
    // Tìm kiếm học sinh theo tên
    @GetMapping("/search")
    public ResponseEntity<List<Student>> searchStudentsByName(@RequestParam String name) {
        List<Student> students = studentService.searchStudentsByName(name);
        return ResponseEntity.ok(students);
    }
    
    // Lấy học sinh theo năm học
    @GetMapping("/academic-year/{academicYear}")
    public ResponseEntity<List<Student>> getStudentsByAcademicYear(@PathVariable String academicYear) {
        List<Student> students = studentService.getStudentsByAcademicYear(academicYear);
        return ResponseEntity.ok(students);
    }
    
    // Lấy học sinh theo khối
    @GetMapping("/grade-level/{gradeLevel}")
    public ResponseEntity<List<Student>> getStudentsByGradeLevel(@PathVariable String gradeLevel) {
        List<Student> students = studentService.getStudentsByGradeLevel(gradeLevel);
        return ResponseEntity.ok(students);
    }
    
    // Tạo học sinh mới
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        if (studentService.existsByStudentCode(student.getStudentCode())) {
            return ResponseEntity.badRequest().build();
        }
        Student savedStudent = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }
    
    // Cập nhật học sinh
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student studentDetails) {
        if (!studentService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        // Kiểm tra mã học sinh trùng lặp (trừ học sinh hiện tại)
        if (studentService.existsByStudentCodeExcludingId(studentDetails.getStudentCode(), id)) {
            return ResponseEntity.badRequest().build();
        }
        
        Student updatedStudent = studentService.updateStudent(id, studentDetails);
        return updatedStudent != null ? ResponseEntity.ok(updatedStudent) : ResponseEntity.notFound().build();
    }
    
    // Vô hiệu hóa học sinh (soft delete)
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateStudent(@PathVariable Long id) {
        if (!studentService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        boolean deactivated = studentService.deactivateStudent(id);
        return deactivated ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    // Xóa học sinh hoàn toàn
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        if (!studentService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        boolean deleted = studentService.deleteStudent(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    // Đếm số học sinh trong lớp
    @GetMapping("/class/{classId}/count")
    public ResponseEntity<Long> getStudentCountInClass(@PathVariable Long classId) {
        Long count = studentService.countStudentsInClass(classId);
        return ResponseEntity.ok(count);
    }
}
