# Sử dụng OpenJDK 17 làm hình ảnh cơ sở
FROM openjdk:17-jdk-slim

# Cài đặt Maven
RUN apt-get update && apt-get install -y maven

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép tất cả tệp từ thư mục hiện tại vào trong container
COPY . /app

# Chạy lệnh Maven để build ứng dụng
RUN mvn clean package -DskipTests

# Lệnh để chạy ứng dụng khi container khởi động
CMD ["java", "-jar", "target/WDA-backend-0.0.1-SNAPSHOT.jar"]
