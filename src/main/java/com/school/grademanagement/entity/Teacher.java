package com.school.grademanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "teachers")
public class Teacher {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Mã giáo viên không được để trống")
    @Size(max = 20, message = "Mã giáo viên không được quá 20 ký tự")
    @Column(name = "teacher_code", nullable = false, unique = true)
    private String teacherCode;
    
    @NotBlank(message = "Họ tên không được để trống")
    @Size(max = 100, message = "Họ tên không được quá 100 ký tự")
    @Column(name = "full_name", nullable = false)
    private String fullName;
    
    @Pattern(regexp = "^(Nam|Nữ)$", message = "Giới tính phải là Nam hoặc Nữ")
    @Column(name = "gender")
    private String gender;
    
    @Size(max = 200, message = "Địa chỉ không được quá 200 ký tự")
    @Column(name = "address")
    private String address;
    
    @Pattern(regexp = "^[0-9]{10,11}$", message = "Số điện thoại phải có 10-11 chữ số")
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Email(message = "Email không hợp lệ")
    @Column(name = "email")
    private String email;
    
    @Size(max = 100, message = "Chuyên môn không được quá 100 ký tự")
    @Column(name = "specialization")
    private String specialization;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    // Quan hệ với ClassEntity (Giáo viên chủ nhiệm)
    @OneToOne(mappedBy = "homeroomTeacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private ClassEntity classEntity;
    
    // Quan hệ với Subject (Giáo viên dạy môn)
    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Subject> subjects;
    
    // Constructors
    public Teacher() {}
    
    public Teacher(String teacherCode, String fullName) {
        this.teacherCode = teacherCode;
        this.fullName = fullName;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTeacherCode() {
        return teacherCode;
    }
    
    public void setTeacherCode(String teacherCode) {
        this.teacherCode = teacherCode;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getSpecialization() {
        return specialization;
    }
    
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    
    public ClassEntity getClassEntity() {
        return classEntity;
    }
    
    public void setClassEntity(ClassEntity classEntity) {
        this.classEntity = classEntity;
    }
    
    public List<Subject> getSubjects() {
        return subjects;
    }
    
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }
    
    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", teacherCode='" + teacherCode + '\'' +
                ", fullName='" + fullName + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
