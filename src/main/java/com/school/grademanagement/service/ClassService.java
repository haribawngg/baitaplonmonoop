package com.school.grademanagement.service;

import com.school.grademanagement.entity.ClassEntity;
import com.school.grademanagement.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClassService {
    
    @Autowired
    private ClassRepository classRepository;
    
    // Lấy tất cả lớp học
    public List<ClassEntity> getAllClasses() {
        return classRepository.findAll();
    }
    
    // Lấy lớp học theo ID
    public Optional<ClassEntity> getClassById(Long id) {
        return classRepository.findById(id);
    }
    
    // Lấy lớp học theo tên lớp
    public Optional<ClassEntity> getClassByName(String className) {
        return classRepository.findByClassName(className);
    }
    
    // Lấy lớp học theo khối
    public List<ClassEntity> getClassesByGradeLevel(String gradeLevel) {
        return classRepository.findByGradeLevel(gradeLevel);
    }
    
    // Lấy lớp học theo năm học
    public List<ClassEntity> getClassesByAcademicYear(String academicYear) {
        return classRepository.findByAcademicYear(academicYear);
    }
    
    // Lấy lớp học theo khối và năm học
    public List<ClassEntity> getClassesByGradeLevelAndAcademicYear(String gradeLevel, String academicYear) {
        return classRepository.findByGradeLevelAndAcademicYear(gradeLevel, academicYear);
    }
    
    // Lấy các lớp đang hoạt động
    public List<ClassEntity> getActiveClasses() {
        return classRepository.findActiveClasses();
    }
    
    // Lưu lớp học mới
    public ClassEntity saveClass(ClassEntity classEntity) {
        return classRepository.save(classEntity);
    }
    
    // Cập nhật lớp học
    public ClassEntity updateClass(Long id, ClassEntity classEntityDetails) {
        Optional<ClassEntity> optionalClass = classRepository.findById(id);
        if (optionalClass.isPresent()) {
            ClassEntity classEntity = optionalClass.get();
            classEntity.setClassName(classEntityDetails.getClassName());
            classEntity.setGradeLevel(classEntityDetails.getGradeLevel());
            classEntity.setAcademicYear(classEntityDetails.getAcademicYear());
            classEntity.setDescription(classEntityDetails.getDescription());
            return classRepository.save(classEntity);
        }
        return null;
    }
    
    // Xóa lớp học
    public boolean deleteClass(Long id) {
        if (classRepository.existsById(id)) {
            classRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Đếm số học sinh trong lớp
    public Long countStudentsInClass(Long classId) {
        return classRepository.countStudentsInClass(classId);
    }
    
    // Lấy lớp học theo giáo viên chủ nhiệm
    public Optional<ClassEntity> getClassByHomeroomTeacher(Long teacherId) {
        return classRepository.findByHomeroomTeacherId(teacherId);
    }
    
    // Kiểm tra lớp học có tồn tại không
    public boolean existsById(Long id) {
        return classRepository.existsById(id);
    }
    
    // Kiểm tra tên lớp có tồn tại không
    public boolean existsByClassName(String className) {
        return classRepository.findByClassName(className).isPresent();
    }
}
