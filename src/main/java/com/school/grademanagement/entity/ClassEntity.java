package com.school.grademanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "classes")
public class ClassEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Tên lớp không được để trống")
    @Size(max = 50, message = "Tên lớp không được quá 50 ký tự")
    @Column(name = "class_name", nullable = false, unique = true)
    private String className;
    
    @NotBlank(message = "Khối không được để trống")
    @Size(max = 10, message = "Khối không được quá 10 ký tự")
    @Column(name = "grade_level", nullable = false)
    private String gradeLevel;
    
    @Column(name = "academic_year")
    private String academicYear;
    
    @Column(name = "description")
    private String description;
    
    // Quan hệ với Student
    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Student> students;
    
    // Quan hệ với Teacher (Giáo viên chủ nhiệm)
    @OneToOne(mappedBy = "classEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Teacher homeroomTeacher;
    
    // Constructors
    public ClassEntity() {}
    
    public ClassEntity(String className, String gradeLevel, String academicYear) {
        this.className = className;
        this.gradeLevel = gradeLevel;
        this.academicYear = academicYear;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getClassName() {
        return className;
    }
    
    public void setClassName(String className) {
        this.className = className;
    }
    
    public String getGradeLevel() {
        return gradeLevel;
    }
    
    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }
    
    public String getAcademicYear() {
        return academicYear;
    }
    
    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public List<Student> getStudents() {
        return students;
    }
    
    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
    public Teacher getHomeroomTeacher() {
        return homeroomTeacher;
    }
    
    public void setHomeroomTeacher(Teacher homeroomTeacher) {
        this.homeroomTeacher = homeroomTeacher;
    }
    
    @Override
    public String toString() {
        return "ClassEntity{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", gradeLevel='" + gradeLevel + '\'' +
                ", academicYear='" + academicYear + '\'' +
                '}';
    }
}
