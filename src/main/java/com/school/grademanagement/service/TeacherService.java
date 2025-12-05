package com.school.grademanagement.service;

import com.school.grademanagement.entity.Teacher;
import com.school.grademanagement.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherService {
    
    @Autowired
    private TeacherRepository teacherRepository;
    
    // Lấy tất cả giáo viên
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
    
    // Lấy giáo viên đang hoạt động
    public List<Teacher> getActiveTeachers() {
        return teacherRepository.findByIsActiveTrue();
    }
    
    // Lấy giáo viên theo ID
    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }
    
    // Lấy giáo viên theo mã giáo viên
    public Optional<Teacher> getTeacherByCode(String teacherCode) {
        return teacherRepository.findByTeacherCode(teacherCode);
    }
    
    // Lấy giáo viên đang hoạt động theo mã giáo viên
    public Optional<Teacher> getActiveTeacherByCode(String teacherCode) {
        return teacherRepository.findByTeacherCodeAndIsActiveTrue(teacherCode);
    }
    
    // Tìm kiếm giáo viên theo tên
    public List<Teacher> searchTeachersByName(String name) {
        return teacherRepository.findByFullNameContaining(name);
    }
    
    // Tìm kiếm giáo viên theo chuyên môn
    public List<Teacher> searchTeachersBySpecialization(String specialization) {
        return teacherRepository.findBySpecializationContaining(specialization);
    }
    
    // Lấy giáo viên chủ nhiệm
    public List<Teacher> getHomeroomTeachers() {
        return teacherRepository.findHomeroomTeachers();
    }
    
    // Lấy giáo viên không phải chủ nhiệm
    public List<Teacher> getNonHomeroomTeachers() {
        return teacherRepository.findNonHomeroomTeachers();
    }
    
    // Lưu giáo viên mới
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }
    
    // Cập nhật giáo viên
    public Teacher updateTeacher(Long id, Teacher teacherDetails) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacher.setTeacherCode(teacherDetails.getTeacherCode());
            teacher.setFullName(teacherDetails.getFullName());
            teacher.setGender(teacherDetails.getGender());
            teacher.setAddress(teacherDetails.getAddress());
            teacher.setPhoneNumber(teacherDetails.getPhoneNumber());
            teacher.setEmail(teacherDetails.getEmail());
            teacher.setSpecialization(teacherDetails.getSpecialization());
            teacher.setIsActive(teacherDetails.getIsActive());
            return teacherRepository.save(teacher);
        }
        return null;
    }
    
    // Xóa giáo viên (soft delete)
    public boolean deactivateTeacher(Long id) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(id);
        if (optionalTeacher.isPresent()) {
            Teacher teacher = optionalTeacher.get();
            teacher.setIsActive(false);
            teacherRepository.save(teacher);
            return true;
        }
        return false;
    }
    
    // Xóa giáo viên hoàn toàn
    public boolean deleteTeacher(Long id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Đếm số giáo viên đang hoạt động
    public Long countActiveTeachers() {
        return teacherRepository.countByIsActiveTrue();
    }
    
    // Kiểm tra giáo viên có tồn tại không
    public boolean existsById(Long id) {
        return teacherRepository.existsById(id);
    }
    
    // Kiểm tra mã giáo viên có tồn tại không
    public boolean existsByTeacherCode(String teacherCode) {
        return teacherRepository.findByTeacherCode(teacherCode).isPresent();
    }
    
    // Kiểm tra mã giáo viên có tồn tại không (trừ giáo viên hiện tại)
    public boolean existsByTeacherCodeExcludingId(String teacherCode, Long id) {
        Optional<Teacher> teacher = teacherRepository.findByTeacherCode(teacherCode);
        return teacher.isPresent() && !teacher.get().getId().equals(id);
    }
}
