# Base image: OpenJDK 17
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc
WORKDIR /app

# Copy toàn bộ mã nguồn vào container
COPY . /app

# Cài Maven và build app
RUN apt-get update && apt-get install -y maven && mvn clean package -DskipTests

# Chạy ứng dụng
CMD ["java", "-jar", "target/WDA-backend-0.0.1-SNAPSHOT.jar"]
