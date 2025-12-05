package com.school.grademanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "grades")
public class Grade {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Điểm số không được để trống")
    @DecimalMin(value = "0.0", message = "Điểm số không được nhỏ hơn 0")
    @DecimalMax(value = "10.0", message = "Điểm số không được lớn hơn 10")
    @Column(name = "score", nullable = false)
    private Double score;
    
    @Column(name = "grade_type")
    private String gradeType; // "Miệng", "15 phút", "1 tiết", "Học kỳ", "Cuối năm"
    
    @Column(name = "semester")
    private String semester; // "Học kỳ 1", "Học kỳ 2"
    
    @Column(name = "academic_year")
    private String academicYear;
    
    @Column(name = "exam_date")
    private LocalDate examDate;
    
    @Column(name = "notes")
    private String notes;
    
    @Column(name = "created_date")
    private LocalDate createdDate;
    
    @Column(name = "updated_date")
    private LocalDate updatedDate;
    
    // Quan hệ với Student
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;
    
    // Quan hệ với Subject
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;
    
    // Constructors
    public Grade() {}
    
    public Grade(Double score, String gradeType, Student student, Subject subject) {
        this.score = score;
        this.gradeType = gradeType;
        this.student = student;
        this.subject = subject;
        this.createdDate = LocalDate.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Double getScore() {
        return score;
    }
    
    public void setScore(Double score) {
        this.score = score;
    }
    
    public String getGradeType() {
        return gradeType;
    }
    
    public void setGradeType(String gradeType) {
        this.gradeType = gradeType;
    }
    
    public String getSemester() {
        return semester;
    }
    
    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    public String getAcademicYear() {
        return academicYear;
    }
    
    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
    
    public LocalDate getExamDate() {
        return examDate;
    }
    
    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public LocalDate getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }
    
    public LocalDate getUpdatedDate() {
        return updatedDate;
    }
    
    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }
    
    public Student getStudent() {
        return student;
    }
    
    public void setStudent(Student student) {
        this.student = student;
    }
    
    public Subject getSubject() {
        return subject;
    }
    
    public void setSubject(Subject subject) {
        this.subject = subject;
    }
    
    // Helper method để tính điểm trung bình
    public String getGradeLevel() {
        if (score >= 9.0) return "Xuất sắc";
        else if (score >= 8.0) return "Giỏi";
        else if (score >= 6.5) return "Khá";
        else if (score >= 5.0) return "Trung bình";
        else if (score >= 3.5) return "Yếu";
        else return "Kém";
    }
    
    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", score=" + score +
                ", gradeType='" + gradeType + '\'' +
                ", semester='" + semester + '\'' +
                ", student=" + (student != null ? student.getFullName() : null) +
                ", subject=" + (subject != null ? subject.getSubjectName() : null) +
                '}';
    }
}
