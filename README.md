# WDA-BE
# Hành trình di sản (Heritage Journey)

Dự án "Hành trình di sản" là một trò chơi mô phỏng du hành thời gian, cho phép người chơi tham gia vào một bảo tàng giả tưởng từ tương lai (Museum of the Future) nơi kết hợp giữa yếu tố hiện đại như trí tuệ nhân tạo, chuyển đổi số đan xen quá khứ bao gồm những hiện vật lịch sử và di sản văn hóa độc đáo của Việt Nam.

## Tính năng chính

- **Quản lý người chơi:**: Đăng ký, đăng nhập, lưu trữ thông tin người dùng
- **Quản lý cổ vật và bảo tàng:**: Thêm, cập nhật, xóa dữ liệu cổ vật và các không gian bảo tàng
- **Lưu trữ tiến trình trò chơi:**: Ghi nhận hành trình và thành tựu của người chơi
- **API cho frontend:**: Cung cấp dữ liệu thời gian thực cho ứng dụng React
- **Tích hợp bảo mật:**: JWT authentication và bảo vệ API

## Cài đặt và Sử dụng

### Yêu cầu

- JDK (v17+)
- Maven (hoặc dùng ./mvnw nếu chưa cài Maven)

### Cài đặt

1. Clone repository:
```
git clone https://github.com/your-username/heritage-journey-backend.git
cd heritage-journey-backend
```

2. Cài dependencies và build project:
- Nếu đã cài maven
```
mvn clean install
```

- Nếu chưa cài maven
```
./mvnw clean install
```

5. Chạy ứng dụng ở chế độ development:
- Nếu đã cài maven
```
mvn spring-boot:run
```

- Nếu chưa cài maven
```
./mvnw spring-boot:run
```

## Công nghệ sử dụng

### Backend
- Springboot
- Spring Security (JWT)
- Hibernate & JPA
- MySQL
- Maven


## Đóng góp

Mọi đóng góp đều được chào đón. Vui lòng tạo pull request hoặc issue để đóng góp cho dự án. 
