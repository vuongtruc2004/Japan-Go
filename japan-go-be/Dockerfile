# ------ STAGE 1: builder ------
# Nhiệm vụ là gradle build => bootJar => app.jar
FROM gradle:9-jdk21-corretto-al2023 AS builder

WORKDIR /app

COPY gradlew .
COPY gradlew.bat .
COPY settings.gradle.kts .
COPY build.gradle.kts .

COPY gradle ./gradle

COPY core/contract/build.gradle.kts ./core/contract/
COPY core/infrastructure/build.gradle.kts ./core/infrastructure/
COPY core/service/build.gradle.kts ./core/service/
COPY core/presentation/build.gradle.kts ./core/presentation/
COPY core/bootstrap/build.gradle.kts ./core/bootstrap/

# Chuyển từ CRLF sang LF và cấp quyền cho ./gradlew
RUN sed -i 's/\r$//' ./gradlew && chmod +x ./gradlew

# preload dependencies
RUN ./gradlew dependencies --no-daemon

# copy source code vào /app/core
COPY core ./core

# sử dụng ./gradlew để không phụ thuộc gradle cài trên máy
# nó tự kiểm tra gradle version và download nếu thiếu
# run build
# kết quả sẽ ra /app/core/bootstrap/build/libs/{tên file}.jar
RUN ./gradlew :core:bootstrap:bootJar --no-daemon

# ------ STAGE 2: runtime ------
FROM eclipse-temurin:21.0.11_10-jre-ubi10-minimal

WORKDIR /app

# groupadd: tạo group trong Linux
# spring là tên group
# useradd: tạo user trong Linux
# -r = system account
# -g = group name (spring)
# spring (ở cuối) là tên user gán vào group tên spring
RUN groupadd spring \
    && useradd -r -g spring spring

# copy file jar từ stage builder vào file /app/app.jar
# dấu "\" để nối dòng
COPY --from=builder \
     --chown=spring:spring \
     /app/core/bootstrap/build/libs/*.jar \
     app.jar

USER spring

EXPOSE 8000

# docker sẽ chạy câu lệnh: java -jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]