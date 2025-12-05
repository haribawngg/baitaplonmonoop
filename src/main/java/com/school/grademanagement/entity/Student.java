package com.school.grademanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Mã học sinh không được để trống")
    @Size(max = 20, message = "Mã học sinh không được quá 20 ký tự")
    @Column(name = "student_code", nullable = false, unique = true)
    private String studentCode;
    
    @NotBlank(message = "Họ tên không được để trống")
    @Size(max = 100, message = "Họ tên không được quá 100 ký tự")
    @Column(name = "full_name", nullable = false)
    private String fullName;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
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
    
    @Column(name = "parent_name")
    private String parentName;
    
    @Pattern(regexp = "^[0-9]{10,11}$", message = "Số điện thoại phụ huynh phải có 10-11 chữ số")
    @Column(name = "parent_phone")
    private String parentPhone;
    
    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;
    
    @Column(name = "is_active")
    private Boolean isActive = true;
    
    // Quan hệ với ClassEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private ClassEntity classEntity;
    
    // Quan hệ với Grade
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Grade> grades;
    
    // Constructors
    public Student() {}
    
    public Student(String studentCode, String fullName, ClassEntity classEntity) {
        this.studentCode = studentCode;
        this.fullName = fullName;
        this.classEntity = classEntity;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getStudentCode() {
        return studentCode;
    }
    
    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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
    
    public String getParentName() {
        return parentName;
    }
    
    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
    
    public String getParentPhone() {
        return parentPhone;
    }
    
    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }
    
    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }
    
    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
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
    
    public List<Grade> getGrades() {
        return grades;
    }
    
    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }
    
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentCode='" + studentCode + '\'' +
                ", fullName='" + fullName + '\'' +
                ", classEntity=" + (classEntity != null ? classEntity.getClassName() : null) +
                '}';
    }
}
