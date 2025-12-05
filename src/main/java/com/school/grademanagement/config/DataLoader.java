package com.school.grademanagement.config;

import com.school.grademanagement.entity.*;
import com.school.grademanagement.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Override
    public void run(String... args) throws Exception {
        // Tạo dữ liệu mẫu nếu chưa có
        if (classRepository.count() == 0) {
            createSampleData();
        }
    }

    private void createSampleData() {
        // Tạo lớp học
        ClassEntity class6A = new ClassEntity("6A", "6", "2023-2024");
        ClassEntity class6B = new ClassEntity("6B", "6", "2023-2024");
        ClassEntity class7A = new ClassEntity("7A", "7", "2023-2024");
        ClassEntity class7B = new ClassEntity("7B", "7", "2023-2024");
        ClassEntity class8A = new ClassEntity("8A", "8", "2023-2024");
        ClassEntity class8B = new ClassEntity("8B", "8", "2023-2024");
        ClassEntity class9A = new ClassEntity("9A", "9", "2023-2024");
        ClassEntity class9B = new ClassEntity("9B", "9", "2023-2024");

        classRepository.saveAll(Arrays.asList(class6A, class6B, class7A, class7B, class8A, class8B, class9A, class9B));

        // Tạo giáo viên
        Teacher teacher1 = new Teacher("GV001", "Nguyễn Văn An");
        teacher1.setGender("Nam");
        teacher1.setSpecialization("Toán học");
        teacher1.setEmail("nguyenvanan@school.edu.vn");
        teacher1.setPhoneNumber("0123456789");

        Teacher teacher2 = new Teacher("GV002", "Trần Thị Bình");
        teacher2.setGender("Nữ");
        teacher2.setSpecialization("Ngữ văn");
        teacher2.setEmail("tranthibinh@school.edu.vn");
        teacher2.setPhoneNumber("0123456790");

        Teacher teacher3 = new Teacher("GV003", "Lê Văn Cường");
        teacher3.setGender("Nam");
        teacher3.setSpecialization("Vật lý");
        teacher3.setEmail("levancuong@school.edu.vn");
        teacher3.setPhoneNumber("0123456791");

        Teacher teacher4 = new Teacher("GV004", "Phạm Thị Dung");
        teacher4.setGender("Nữ");
        teacher4.setSpecialization("Hóa học");
        teacher4.setEmail("phamthidung@school.edu.vn");
        teacher4.setPhoneNumber("0123456792");

        teacherRepository.saveAll(Arrays.asList(teacher1, teacher2, teacher3, teacher4));

        // Phân công giáo viên chủ nhiệm
        class6A.setHomeroomTeacher(teacher1);
        class6B.setHomeroomTeacher(teacher2);
        class7A.setHomeroomTeacher(teacher3);
        class7B.setHomeroomTeacher(teacher4);

        classRepository.saveAll(Arrays.asList(class6A, class6B, class7A, class7B));

        // Tạo môn học
        Subject math6 = new Subject("MATH6", "Toán học 6", "6");
        math6.setTeacher(teacher1);
        math6.setCredits(4);

        Subject literature6 = new Subject("LIT6", "Ngữ văn 6", "6");
        literature6.setTeacher(teacher2);
        literature6.setCredits(4);

        Subject physics7 = new Subject("PHY7", "Vật lý 7", "7");
        physics7.setTeacher(teacher3);
        physics7.setCredits(3);

        Subject chemistry8 = new Subject("CHEM8", "Hóa học 8", "8");
        chemistry8.setTeacher(teacher4);
        chemistry8.setCredits(3);

        Subject math7 = new Subject("MATH7", "Toán học 7", "7");
        math7.setTeacher(teacher1);
        math7.setCredits(4);

        Subject literature7 = new Subject("LIT7", "Ngữ văn 7", "7");
        literature7.setTeacher(teacher2);
        literature7.setCredits(4);

        subjectRepository.saveAll(Arrays.asList(math6, literature6, physics7, chemistry8, math7, literature7));

        // Tạo học sinh
        Student student1 = new Student("HS001", "Nguyễn Văn Nam", class6A);
        student1.setDateOfBirth(LocalDate.of(2011, 5, 15));
        student1.setGender("Nam");
        student1.setAddress("123 Đường ABC, Quận 1, TP.HCM");
        student1.setPhoneNumber("0123456789");
        student1.setEmail("nguyenvannam@email.com");
        student1.setParentName("Nguyễn Văn Ba");
        student1.setParentPhone("0987654321");
        student1.setEnrollmentDate(LocalDate.of(2023, 9, 1));

        Student student2 = new Student("HS002", "Trần Thị Lan", class6A);
        student2.setDateOfBirth(LocalDate.of(2011, 8, 20));
        student2.setGender("Nữ");
        student2.setAddress("456 Đường DEF, Quận 2, TP.HCM");
        student2.setPhoneNumber("0123456790");
        student2.setEmail("tranthilan@email.com");
        student2.setParentName("Trần Thị Mai");
        student2.setParentPhone("0987654322");
        student2.setEnrollmentDate(LocalDate.of(2023, 9, 1));

        Student student3 = new Student("HS003", "Lê Văn Hùng", class7A);
        student3.setDateOfBirth(LocalDate.of(2010, 3, 10));
        student3.setGender("Nam");
        student3.setAddress("789 Đường GHI, Quận 3, TP.HCM");
        student3.setPhoneNumber("0123456791");
        student3.setEmail("levanhung@email.com");
        student3.setParentName("Lê Văn Minh");
        student3.setParentPhone("0987654323");
        student3.setEnrollmentDate(LocalDate.of(2023, 9, 1));

        Student student4 = new Student("HS004", "Phạm Thị Mai", class7A);
        student4.setDateOfBirth(LocalDate.of(2010, 7, 25));
        student4.setGender("Nữ");
        student4.setAddress("321 Đường JKL, Quận 4, TP.HCM");
        student4.setPhoneNumber("0123456792");
        student4.setEmail("phamthimai@email.com");
        student4.setParentName("Phạm Thị Hoa");
        student4.setParentPhone("0987654324");
        student4.setEnrollmentDate(LocalDate.of(2023, 9, 1));

        studentRepository.saveAll(Arrays.asList(student1, student2, student3, student4));

        // Tạo điểm số mẫu
        Grade grade1 = new Grade(8.5, "Miệng", student1, math6);
        grade1.setSemester("Học kỳ 1");
        grade1.setAcademicYear("2023-2024");
        grade1.setExamDate(LocalDate.of(2023, 10, 15));

        Grade grade2 = new Grade(9.0, "15 phút", student1, math6);
        grade2.setSemester("Học kỳ 1");
        grade2.setAcademicYear("2023-2024");
        grade2.setExamDate(LocalDate.of(2023, 10, 20));

        Grade grade3 = new Grade(7.5, "1 tiết", student1, math6);
        grade3.setSemester("Học kỳ 1");
        grade3.setAcademicYear("2023-2024");
        grade3.setExamDate(LocalDate.of(2023, 11, 5));

        Grade grade4 = new Grade(8.0, "Miệng", student2, literature6);
        grade4.setSemester("Học kỳ 1");
        grade4.setAcademicYear("2023-2024");
        grade4.setExamDate(LocalDate.of(2023, 10, 16));

        Grade grade5 = new Grade(8.5, "15 phút", student2, literature6);
        grade5.setSemester("Học kỳ 1");
        grade5.setAcademicYear("2023-2024");
        grade5.setExamDate(LocalDate.of(2023, 10, 21));

        Grade grade6 = new Grade(9.5, "Miệng", student3, physics7);
        grade6.setSemester("Học kỳ 1");
        grade6.setAcademicYear("2023-2024");
        grade6.setExamDate(LocalDate.of(2023, 10, 17));

        Grade grade7 = new Grade(8.0, "1 tiết", student3, physics7);
        grade7.setSemester("Học kỳ 1");
        grade7.setAcademicYear("2023-2024");
        grade7.setExamDate(LocalDate.of(2023, 11, 6));

        Grade grade8 = new Grade(7.0, "Miệng", student4, chemistry8);
        grade8.setSemester("Học kỳ 1");
        grade8.setAcademicYear("2023-2024");
        grade8.setExamDate(LocalDate.of(2023, 10, 18));

        Grade grade9 = new Grade(8.5, "15 phút", student4, chemistry8);
        grade9.setSemester("Học kỳ 1");
        grade9.setAcademicYear("2023-2024");
        grade9.setExamDate(LocalDate.of(2023, 10, 22));

        Grade grade10 = new Grade(9.0, "1 tiết", student4, chemistry8);
        grade10.setSemester("Học kỳ 1");
        grade10.setAcademicYear("2023-2024");
        grade10.setExamDate(LocalDate.of(2023, 11, 7));

        gradeRepository.saveAll(Arrays.asList(grade1, grade2, grade3, grade4, grade5, grade6, grade7, grade8, grade9, grade10));

        System.out.println("Dữ liệu mẫu đã được tạo thành công!");
    }
}
