# =========================
# STAGE 1: Build
# =========================
FROM gradle:8.7-jdk21 AS builder

WORKDIR /app

# Copy trước các file cấu hình để tận dụng cache
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle gradle

RUN chmod +x gradlew

# Tải dependency trước
RUN ./gradlew dependencies --no-daemon || true

# Copy source
COPY src src

# Build project
RUN ./gradlew clean bootJar --no-daemon


# =========================
# STAGE 2: Run
# =========================
FROM eclipse-temurin:21-jre

WORKDIR /app

ENV TZ=Asia/Ho_Chi_Minh
ENV SPRING_PROFILES_ACTIVE=uat

EXPOSE 2509

# Copy file jar được build từ stage 1
COPY --from=builder /app/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]