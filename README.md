# Hệ thống quản lý điểm số học sinh trường THCS

## Mô tả dự án

Đây là một ứng dụng web được xây dựng bằng Java Spring Boot để quản lý điểm số học sinh trường Trung học cơ sở. Hệ thống cung cấp các tính năng quản lý học sinh, lớp học, môn học, giáo viên và điểm số một cách hiệu quả.

## Tính năng chính

### 1. Quản lý học sinh
- Thêm, sửa, xóa thông tin học sinh
- Tìm kiếm học sinh theo tên
- Phân lớp học sinh
- Quản lý thông tin phụ huynh

### 2. Quản lý lớp học
- Tạo và quản lý các lớp học
- Phân công giáo viên chủ nhiệm
- Quản lý theo khối và năm học

### 3. Quản lý môn học
- Thiết lập các môn học theo khối lớp
- Phân công giáo viên giảng dạy
- Quản lý số tín chỉ môn học

### 4. Quản lý giáo viên
- Quản lý thông tin giáo viên
- Phân công chủ nhiệm lớp
- Quản lý chuyên môn giáo viên

### 5. Quản lý điểm số
- Nhập điểm các loại (Miệng, 15 phút, 1 tiết, Học kỳ, Cuối năm)
- Tự động tính điểm trung bình
- Xếp loại học lực tự động
- Báo cáo điểm theo học kỳ và năm học

## Công nghệ sử dụng

### Backend
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database** (cho development)
- **MySQL** (cho production)
- **Maven** (quản lý dependencies)

### Frontend
- **HTML5**
- **CSS3**
- **JavaScript (ES6+)**
- **Bootstrap 5.3.0**
- **Font Awesome 6.0.0**
- **Thymeleaf** (template engine)

## Cài đặt và chạy ứng dụng

### Yêu cầu hệ thống
- Java 17 hoặc cao hơn
- Maven 3.6 hoặc cao hơn
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Các bước cài đặt

1. **Clone repository**
```bash
git clone <repository-url>
cd grade-management-system
```

2. **Cài đặt dependencies**
```bash
mvn clean install
```

3. **Chạy ứng dụng**
```bash
mvn spring-boot:run
```

4. **Truy cập ứng dụng**
- Mở trình duyệt và truy cập: `http://localhost:8080`
- Truy cập H2 Console: `http://localhost:8080/h2-console`

### Tài khoản đăng nhập demo

**Admin:**
- Username: `admin`
- Password: `admin123`

**Teacher:**
- Username: `teacher`
- Password: `teacher123`

## Cấu trúc dự án

```
src/
├── main/
│   ├── java/
│   │   └── com/school/grademanagement/
│   │       ├── config/          # Cấu hình Spring Security
│   │       ├── controller/      # REST API Controllers
│   │       ├── entity/          # JPA Entities
│   │       ├── repository/      # Spring Data JPA Repositories
│   │       ├── service/         # Business Logic Services
│   │       └── GradeManagementApplication.java
│   └── resources/
│       ├── templates/           # Thymeleaf templates
│       └── application.properties
└── test/
```

## API Endpoints

### Học sinh (Students)
- `GET /api/students` - Lấy danh sách tất cả học sinh
- `GET /api/students/{id}` - Lấy thông tin học sinh theo ID
- `POST /api/students` - Tạo học sinh mới
- `PUT /api/students/{id}` - Cập nhật thông tin học sinh
- `DELETE /api/students/{id}` - Xóa học sinh

### Lớp học (Classes)
- `GET /api/classes` - Lấy danh sách tất cả lớp học
- `GET /api/classes/{id}` - Lấy thông tin lớp học theo ID
- `POST /api/classes` - Tạo lớp học mới
- `PUT /api/classes/{id}` - Cập nhật thông tin lớp học
- `DELETE /api/classes/{id}` - Xóa lớp học

### Môn học (Subjects)
- `GET /api/subjects` - Lấy danh sách tất cả môn học
- `GET /api/subjects/{id}` - Lấy thông tin môn học theo ID
- `POST /api/subjects` - Tạo môn học mới
- `PUT /api/subjects/{id}` - Cập nhật thông tin môn học
- `DELETE /api/subjects/{id}` - Xóa môn học

### Giáo viên (Teachers)
- `GET /api/teachers` - Lấy danh sách tất cả giáo viên
- `GET /api/teachers/{id}` - Lấy thông tin giáo viên theo ID
- `POST /api/teachers` - Tạo giáo viên mới
- `PUT /api/teachers/{id}` - Cập nhật thông tin giáo viên
- `DELETE /api/teachers/{id}` - Xóa giáo viên

### Điểm số (Grades)
- `GET /api/grades` - Lấy danh sách tất cả điểm số
- `GET /api/grades/{id}` - Lấy thông tin điểm số theo ID
- `POST /api/grades` - Nhập điểm số mới
- `PUT /api/grades/{id}` - Cập nhật điểm số
- `DELETE /api/grades/{id}` - Xóa điểm số
- `GET /api/grades/student/{studentId}/average` - Tính điểm trung bình của học sinh

## Tính năng nâng cao

### Xếp loại học lực tự động
Hệ thống tự động xếp loại học lực dựa trên điểm trung bình:
- **Xuất sắc**: ≥ 9.0
- **Giỏi**: 8.0 - 8.9
- **Khá**: 6.5 - 7.9
- **Trung bình**: 5.0 - 6.4
- **Yếu**: 3.5 - 4.9
- **Kém**: < 3.5

### Báo cáo thống kê
- Thống kê điểm số theo lớp
- Thống kê điểm số theo môn học
- Thống kê điểm số theo học kỳ
- Báo cáo tổng kết năm học

## Cấu hình database

### H2 Database (Development)
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
```

### MySQL (Production)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/grade_management
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

## Đóng góp

Nếu bạn muốn đóng góp vào dự án, vui lòng:

1. Fork repository
2. Tạo feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to branch (`git push origin feature/AmazingFeature`)
5. Tạo Pull Request

## License

Dự án này được phân phối dưới MIT License. Xem file `LICENSE` để biết thêm thông tin.

## Liên hệ

Nếu có bất kỳ câu hỏi nào, vui lòng liên hệ qua email hoặc tạo issue trên GitHub.

---

**Lưu ý**: Đây là phiên bản demo của hệ thống quản lý điểm số. Trong môi trường production, cần thêm các tính năng bảo mật và xác thực nâng cao hơn.
