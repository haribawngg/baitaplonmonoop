package com.school.grademanagement.service;

import com.school.grademanagement.entity.Subject;
import com.school.grademanagement.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubjectService {
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    // Lấy tất cả môn học
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }
    
    // Lấy môn học đang hoạt động
    public List<Subject> getActiveSubjects() {
        return subjectRepository.findByIsActiveTrue();
    }
    
    // Lấy môn học theo ID
    public Optional<Subject> getSubjectById(Long id) {
        return subjectRepository.findById(id);
    }
    
    // Lấy môn học theo mã môn học
    public Optional<Subject> getSubjectByCode(String subjectCode) {
        return subjectRepository.findBySubjectCode(subjectCode);
    }
    
    // Lấy môn học đang hoạt động theo mã môn học
    public Optional<Subject> getActiveSubjectByCode(String subjectCode) {
        return subjectRepository.findBySubjectCodeAndIsActiveTrue(subjectCode);
    }
    
    // Tìm kiếm môn học theo tên
    public List<Subject> searchSubjectsByName(String name) {
        return subjectRepository.findBySubjectNameContaining(name);
    }
    
    // Lấy môn học theo khối
    public List<Subject> getSubjectsByGradeLevel(String gradeLevel) {
        return subjectRepository.findByGradeLevelAndIsActiveTrue(gradeLevel);
    }
    
    // Lấy môn học theo giáo viên
    public List<Subject> getSubjectsByTeacher(Long teacherId) {
        return subjectRepository.findByTeacherId(teacherId);
    }
    
    // Lấy môn học theo giáo viên và khối
    public List<Subject> getSubjectsByTeacherAndGradeLevel(Long teacherId, String gradeLevel) {
        return subjectRepository.findByTeacherIdAndGradeLevel(teacherId, gradeLevel);
    }
    
    // Lưu môn học mới
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }
    
    // Cập nhật môn học
    public Subject updateSubject(Long id, Subject subjectDetails) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            Subject subject = optionalSubject.get();
            subject.setSubjectCode(subjectDetails.getSubjectCode());
            subject.setSubjectName(subjectDetails.getSubjectName());
            subject.setGradeLevel(subjectDetails.getGradeLevel());
            subject.setCredits(subjectDetails.getCredits());
            subject.setDescription(subjectDetails.getDescription());
            subject.setIsActive(subjectDetails.getIsActive());
            subject.setTeacher(subjectDetails.getTeacher());
            return subjectRepository.save(subject);
        }
        return null;
    }
    
    // Xóa môn học (soft delete)
    public boolean deactivateSubject(Long id) {
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()) {
            Subject subject = optionalSubject.get();
            subject.setIsActive(false);
            subjectRepository.save(subject);
            return true;
        }
        return false;
    }
    
    // Xóa môn học hoàn toàn
    public boolean deleteSubject(Long id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Đếm số môn học đang hoạt động
    public Long countActiveSubjects() {
        return subjectRepository.countByIsActiveTrue();
    }
    
    // Đếm số môn học theo khối
    public Long countSubjectsByGradeLevel(String gradeLevel) {
        return subjectRepository.countByGradeLevelAndIsActiveTrue(gradeLevel);
    }
    
    // Kiểm tra môn học có tồn tại không
    public boolean existsById(Long id) {
        return subjectRepository.existsById(id);
    }
    
    // Kiểm tra mã môn học có tồn tại không
    public boolean existsBySubjectCode(String subjectCode) {
        return subjectRepository.findBySubjectCode(subjectCode).isPresent();
    }
    
    // Kiểm tra mã môn học có tồn tại không (trừ môn học hiện tại)
    public boolean existsBySubjectCodeExcludingId(String subjectCode, Long id) {
        Optional<Subject> subject = subjectRepository.findBySubjectCode(subjectCode);
        return subject.isPresent() && !subject.get().getId().equals(id);
    }
}
