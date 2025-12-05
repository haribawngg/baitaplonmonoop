package com.school.grademanagement.service;

import com.school.grademanagement.entity.Grade;
import com.school.grademanagement.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GradeService {
    
    @Autowired
    private GradeRepository gradeRepository;
    
    // Lấy tất cả điểm
    public List<Grade> getAllGrades() {
        return gradeRepository.findAll();
    }
    
    // Lấy điểm theo ID
    public Optional<Grade> getGradeById(Long id) {
        return gradeRepository.findById(id);
    }
    
    // Lấy điểm theo học sinh
    public List<Grade> getGradesByStudent(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }
    
    // Lấy điểm theo môn học
    public List<Grade> getGradesBySubject(Long subjectId) {
        return gradeRepository.findBySubjectId(subjectId);
    }
    
    // Lấy điểm theo học sinh và môn học
    public List<Grade> getGradesByStudentAndSubject(Long studentId, Long subjectId) {
        return gradeRepository.findByStudentIdAndSubjectId(studentId, subjectId);
    }
    
    // Lấy điểm theo học sinh, môn học và loại điểm
    public List<Grade> getGradesByStudentAndSubjectAndType(Long studentId, Long subjectId, String gradeType) {
        return gradeRepository.findByStudentIdAndSubjectIdAndGradeType(studentId, subjectId, gradeType);
    }
    
    // Lấy điểm theo học sinh, môn học và học kỳ
    public List<Grade> getGradesByStudentAndSubjectAndSemester(Long studentId, Long subjectId, String semester) {
        return gradeRepository.findByStudentIdAndSubjectIdAndSemester(studentId, subjectId, semester);
    }
    
    // Lấy điểm theo lớp và môn học
    public List<Grade> getGradesByClassAndSubject(Long classId, Long subjectId) {
        return gradeRepository.findByClassIdAndSubjectId(classId, subjectId);
    }
    
    // Lấy điểm theo lớp, môn học và học kỳ
    public List<Grade> getGradesByClassAndSubjectAndSemester(Long classId, Long subjectId, String semester) {
        return gradeRepository.findByClassIdAndSubjectIdAndSemester(classId, subjectId, semester);
    }
    
    // Lấy điểm theo khoảng thời gian
    public List<Grade> getGradesByDateRange(LocalDate startDate, LocalDate endDate) {
        return gradeRepository.findByExamDateBetween(startDate, endDate);
    }
    
    // Lấy điểm theo năm học
    public List<Grade> getGradesByAcademicYear(String academicYear) {
        return gradeRepository.findByAcademicYear(academicYear);
    }
    
    // Lấy điểm theo năm học và học kỳ
    public List<Grade> getGradesByAcademicYearAndSemester(String academicYear, String semester) {
        return gradeRepository.findByAcademicYearAndSemester(academicYear, semester);
    }
    
    // Lưu điểm mới
    public Grade saveGrade(Grade grade) {
        grade.setCreatedDate(LocalDate.now());
        return gradeRepository.save(grade);
    }
    
    // Cập nhật điểm
    public Grade updateGrade(Long id, Grade gradeDetails) {
        Optional<Grade> optionalGrade = gradeRepository.findById(id);
        if (optionalGrade.isPresent()) {
            Grade grade = optionalGrade.get();
            grade.setScore(gradeDetails.getScore());
            grade.setGradeType(gradeDetails.getGradeType());
            grade.setSemester(gradeDetails.getSemester());
            grade.setAcademicYear(gradeDetails.getAcademicYear());
            grade.setExamDate(gradeDetails.getExamDate());
            grade.setNotes(gradeDetails.getNotes());
            grade.setUpdatedDate(LocalDate.now());
            return gradeRepository.save(grade);
        }
        return null;
    }
    
    // Xóa điểm
    public boolean deleteGrade(Long id) {
        if (gradeRepository.existsById(id)) {
            gradeRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Tính điểm trung bình của học sinh theo môn học và học kỳ
    public Optional<Double> calculateAverageByStudentAndSubjectAndSemester(Long studentId, Long subjectId, String semester) {
        return gradeRepository.calculateAverageByStudentAndSubjectAndSemester(studentId, subjectId, semester);
    }
    
    // Tính điểm trung bình của học sinh theo môn học (cả năm)
    public Optional<Double> calculateAverageByStudentAndSubject(Long studentId, Long subjectId) {
        return gradeRepository.calculateAverageByStudentAndSubject(studentId, subjectId);
    }
    
    // Tính điểm trung bình của học sinh theo học kỳ
    public Optional<Double> calculateAverageByStudentAndSemester(Long studentId, String semester) {
        return gradeRepository.calculateAverageByStudentAndSemester(studentId, semester);
    }
    
    // Tính điểm trung bình của học sinh (cả năm)
    public Optional<Double> calculateAverageByStudent(Long studentId) {
        return gradeRepository.calculateAverageByStudent(studentId);
    }
    
    // Đếm số điểm của học sinh theo môn học
    public Long countGradesByStudentAndSubject(Long studentId, Long subjectId) {
        return gradeRepository.countByStudentIdAndSubjectId(studentId, subjectId);
    }
    
    // Tìm điểm cao nhất của học sinh theo môn học
    public Optional<Double> findMaxScoreByStudentAndSubject(Long studentId, Long subjectId) {
        return gradeRepository.findMaxScoreByStudentAndSubject(studentId, subjectId);
    }
    
    // Tìm điểm thấp nhất của học sinh theo môn học
    public Optional<Double> findMinScoreByStudentAndSubject(Long studentId, Long subjectId) {
        return gradeRepository.findMinScoreByStudentAndSubject(studentId, subjectId);
    }
    
    // Kiểm tra điểm có tồn tại không
    public boolean existsById(Long id) {
        return gradeRepository.existsById(id);
    }
    
    // Tính điểm trung bình và xếp loại học lực
    public String calculateGradeLevel(Double averageScore) {
        if (averageScore >= 9.0) return "Xuất sắc";
        else if (averageScore >= 8.0) return "Giỏi";
        else if (averageScore >= 6.5) return "Khá";
        else if (averageScore >= 5.0) return "Trung bình";
        else if (averageScore >= 3.5) return "Yếu";
        else return "Kém";
    }
}
