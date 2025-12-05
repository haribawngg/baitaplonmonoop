package com.school.grademanagement.service;

import com.school.grademanagement.entity.Student;
import com.school.grademanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    // Lấy tất cả học sinh
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    // Lấy học sinh theo ID
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
    
    // Lấy học sinh theo mã học sinh
    public Optional<Student> getStudentByCode(String studentCode) {
        return studentRepository.findByStudentCode(studentCode);
    }
    
    // Lấy học sinh đang hoạt động theo mã học sinh
    public Optional<Student> getActiveStudentByCode(String studentCode) {
        return studentRepository.findByStudentCodeAndIsActiveTrue(studentCode);
    }
    
    // Lấy học sinh theo lớp
    public List<Student> getStudentsByClass(Long classId) {
        return studentRepository.findByClassEntityId(classId);
    }
    
    // Lấy học sinh đang hoạt động theo lớp
    public List<Student> getActiveStudentsByClass(Long classId) {
        return studentRepository.findActiveStudentsByClass(classId);
    }
    
    // Tìm kiếm học sinh theo tên
    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByFullNameContaining(name);
    }
    
    // Lấy học sinh theo năm học
    public List<Student> getStudentsByAcademicYear(String academicYear) {
        return studentRepository.findByAcademicYear(academicYear);
    }
    
    // Lấy học sinh theo khối
    public List<Student> getStudentsByGradeLevel(String gradeLevel) {
        return studentRepository.findByGradeLevel(gradeLevel);
    }
    
    // Lưu học sinh mới
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    
    // Cập nhật học sinh
    public Student updateStudent(Long id, Student studentDetails) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setStudentCode(studentDetails.getStudentCode());
            student.setFullName(studentDetails.getFullName());
            student.setDateOfBirth(studentDetails.getDateOfBirth());
            student.setGender(studentDetails.getGender());
            student.setAddress(studentDetails.getAddress());
            student.setPhoneNumber(studentDetails.getPhoneNumber());
            student.setEmail(studentDetails.getEmail());
            student.setParentName(studentDetails.getParentName());
            student.setParentPhone(studentDetails.getParentPhone());
            student.setEnrollmentDate(studentDetails.getEnrollmentDate());
            student.setIsActive(studentDetails.getIsActive());
            student.setClassEntity(studentDetails.getClassEntity());
            return studentRepository.save(student);
        }
        return null;
    }
    
    // Xóa học sinh (soft delete)
    public boolean deactivateStudent(Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setIsActive(false);
            studentRepository.save(student);
            return true;
        }
        return false;
    }
    
    // Xóa học sinh hoàn toàn
    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Đếm số học sinh trong lớp
    public Long countStudentsInClass(Long classId) {
        return studentRepository.countByClassEntityIdAndIsActiveTrue(classId);
    }
    
    // Kiểm tra học sinh có tồn tại không
    public boolean existsById(Long id) {
        return studentRepository.existsById(id);
    }
    
    // Kiểm tra mã học sinh có tồn tại không
    public boolean existsByStudentCode(String studentCode) {
        return studentRepository.findByStudentCode(studentCode).isPresent();
    }
    
    // Kiểm tra mã học sinh có tồn tại không (trừ học sinh hiện tại)
    public boolean existsByStudentCodeExcludingId(String studentCode, Long id) {
        Optional<Student> student = studentRepository.findByStudentCode(studentCode);
        return student.isPresent() && !student.get().getId().equals(id);
    }
}
