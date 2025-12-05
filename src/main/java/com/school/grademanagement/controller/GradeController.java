package com.school.grademanagement.controller;

import com.school.grademanagement.entity.Grade;
import com.school.grademanagement.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/grades")
@CrossOrigin(origins = "*")
public class GradeController {
    
    @Autowired
    private GradeService gradeService;
    
    // Lấy tất cả điểm
    @GetMapping
    public ResponseEntity<List<Grade>> getAllGrades() {
        List<Grade> grades = gradeService.getAllGrades();
        return ResponseEntity.ok(grades);
    }
    
    // Lấy điểm theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Grade> getGradeById(@PathVariable Long id) {
        Optional<Grade> grade = gradeService.getGradeById(id);
        return grade.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Lấy điểm theo học sinh
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Grade>> getGradesByStudent(@PathVariable Long studentId) {
        List<Grade> grades = gradeService.getGradesByStudent(studentId);
        return ResponseEntity.ok(grades);
    }
    
    // Lấy điểm theo môn học
    @GetMapping("/subject/{subjectId}")
    public ResponseEntity<List<Grade>> getGradesBySubject(@PathVariable Long subjectId) {
        List<Grade> grades = gradeService.getGradesBySubject(subjectId);
        return ResponseEntity.ok(grades);
    }
    
    // Lấy điểm theo học sinh và môn học
    @GetMapping("/student/{studentId}/subject/{subjectId}")
    public ResponseEntity<List<Grade>> getGradesByStudentAndSubject(
            @PathVariable Long studentId, 
            @PathVariable Long subjectId) {
        List<Grade> grades = gradeService.getGradesByStudentAndSubject(studentId, subjectId);
        return ResponseEntity.ok(grades);
    }
    
    // Lấy điểm theo học sinh, môn học và loại điểm
    @GetMapping("/student/{studentId}/subject/{subjectId}/type/{gradeType}")
    public ResponseEntity<List<Grade>> getGradesByStudentAndSubjectAndType(
            @PathVariable Long studentId, 
            @PathVariable Long subjectId,
            @PathVariable String gradeType) {
        List<Grade> grades = gradeService.getGradesByStudentAndSubjectAndType(studentId, subjectId, gradeType);
        return ResponseEntity.ok(grades);
    }
    
    // Lấy điểm theo học sinh, môn học và học kỳ
    @GetMapping("/student/{studentId}/subject/{subjectId}/semester/{semester}")
    public ResponseEntity<List<Grade>> getGradesByStudentAndSubjectAndSemester(
            @PathVariable Long studentId, 
            @PathVariable Long subjectId,
            @PathVariable String semester) {
        List<Grade> grades = gradeService.getGradesByStudentAndSubjectAndSemester(studentId, subjectId, semester);
        return ResponseEntity.ok(grades);
    }
    
    // Lấy điểm theo lớp và môn học
    @GetMapping("/class/{classId}/subject/{subjectId}")
    public ResponseEntity<List<Grade>> getGradesByClassAndSubject(
            @PathVariable Long classId, 
            @PathVariable Long subjectId) {
        List<Grade> grades = gradeService.getGradesByClassAndSubject(classId, subjectId);
        return ResponseEntity.ok(grades);
    }
    
    // Lấy điểm theo lớp, môn học và học kỳ
    @GetMapping("/class/{classId}/subject/{subjectId}/semester/{semester}")
    public ResponseEntity<List<Grade>> getGradesByClassAndSubjectAndSemester(
            @PathVariable Long classId, 
            @PathVariable Long subjectId,
            @PathVariable String semester) {
        List<Grade> grades = gradeService.getGradesByClassAndSubjectAndSemester(classId, subjectId, semester);
        return ResponseEntity.ok(grades);
    }
    
    // Lấy điểm theo khoảng thời gian
    @GetMapping("/date-range")
    public ResponseEntity<List<Grade>> getGradesByDateRange(
            @RequestParam LocalDate startDate, 
            @RequestParam LocalDate endDate) {
        List<Grade> grades = gradeService.getGradesByDateRange(startDate, endDate);
        return ResponseEntity.ok(grades);
    }
    
    // Lấy điểm theo năm học
    @GetMapping("/academic-year/{academicYear}")
    public ResponseEntity<List<Grade>> getGradesByAcademicYear(@PathVariable String academicYear) {
        List<Grade> grades = gradeService.getGradesByAcademicYear(academicYear);
        return ResponseEntity.ok(grades);
    }
    
    // Lấy điểm theo năm học và học kỳ
    @GetMapping("/academic-year/{academicYear}/semester/{semester}")
    public ResponseEntity<List<Grade>> getGradesByAcademicYearAndSemester(
            @PathVariable String academicYear, 
            @PathVariable String semester) {
        List<Grade> grades = gradeService.getGradesByAcademicYearAndSemester(academicYear, semester);
        return ResponseEntity.ok(grades);
    }
    
    // Tạo điểm mới
    @PostMapping
    public ResponseEntity<Grade> createGrade(@Valid @RequestBody Grade grade) {
        Grade savedGrade = gradeService.saveGrade(grade);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGrade);
    }
    
    // Cập nhật điểm
    @PutMapping("/{id}")
    public ResponseEntity<Grade> updateGrade(@PathVariable Long id, @Valid @RequestBody Grade gradeDetails) {
        if (!gradeService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        Grade updatedGrade = gradeService.updateGrade(id, gradeDetails);
        return updatedGrade != null ? ResponseEntity.ok(updatedGrade) : ResponseEntity.notFound().build();
    }
    
    // Xóa điểm
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGrade(@PathVariable Long id) {
        if (!gradeService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        
        boolean deleted = gradeService.deleteGrade(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
    
    // Tính điểm trung bình của học sinh theo môn học và học kỳ
    @GetMapping("/student/{studentId}/subject/{subjectId}/semester/{semester}/average")
    public ResponseEntity<Double> calculateAverageByStudentAndSubjectAndSemester(
            @PathVariable Long studentId, 
            @PathVariable Long subjectId,
            @PathVariable String semester) {
        Optional<Double> average = gradeService.calculateAverageByStudentAndSubjectAndSemester(studentId, subjectId, semester);
        return average.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Tính điểm trung bình của học sinh theo môn học (cả năm)
    @GetMapping("/student/{studentId}/subject/{subjectId}/average")
    public ResponseEntity<Double> calculateAverageByStudentAndSubject(
            @PathVariable Long studentId, 
            @PathVariable Long subjectId) {
        Optional<Double> average = gradeService.calculateAverageByStudentAndSubject(studentId, subjectId);
        return average.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Tính điểm trung bình của học sinh theo học kỳ
    @GetMapping("/student/{studentId}/semester/{semester}/average")
    public ResponseEntity<Double> calculateAverageByStudentAndSemester(
            @PathVariable Long studentId, 
            @PathVariable String semester) {
        Optional<Double> average = gradeService.calculateAverageByStudentAndSemester(studentId, semester);
        return average.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Tính điểm trung bình của học sinh (cả năm)
    @GetMapping("/student/{studentId}/average")
    public ResponseEntity<Double> calculateAverageByStudent(@PathVariable Long studentId) {
        Optional<Double> average = gradeService.calculateAverageByStudent(studentId);
        return average.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Đếm số điểm của học sinh theo môn học
    @GetMapping("/student/{studentId}/subject/{subjectId}/count")
    public ResponseEntity<Long> getGradeCountByStudentAndSubject(
            @PathVariable Long studentId, 
            @PathVariable Long subjectId) {
        Long count = gradeService.countGradesByStudentAndSubject(studentId, subjectId);
        return ResponseEntity.ok(count);
    }
    
    // Tìm điểm cao nhất của học sinh theo môn học
    @GetMapping("/student/{studentId}/subject/{subjectId}/max")
    public ResponseEntity<Double> getMaxScoreByStudentAndSubject(
            @PathVariable Long studentId, 
            @PathVariable Long subjectId) {
        Optional<Double> maxScore = gradeService.findMaxScoreByStudentAndSubject(studentId, subjectId);
        return maxScore.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    // Tìm điểm thấp nhất của học sinh theo môn học
    @GetMapping("/student/{studentId}/subject/{subjectId}/min")
    public ResponseEntity<Double> getMinScoreByStudentAndSubject(
            @PathVariable Long studentId, 
            @PathVariable Long subjectId) {
        Optional<Double> minScore = gradeService.findMinScoreByStudentAndSubject(studentId, subjectId);
        return minScore.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
