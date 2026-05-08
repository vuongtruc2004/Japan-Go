# =========================
# STAGE 1: Build
# =========================
FROM gradle:8.7-jdk21 AS builder

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY settings.gradle.kts .
COPY build.gradle.kts .

COPY core/bootstrap/build.gradle.kts core/bootstrap/build.gradle.kts
COPY core/contract/build.gradle.kts core/contract/build.gradle.kts
COPY core/infrastructure/build.gradle.kts core/infrastructure/build.gradle.kts
COPY core/presentation/build.gradle.kts core/presentation/build.gradle.kts
COPY core/service/build.gradle.kts core/service/build.gradle.kts

RUN chmod +x gradlew

RUN ./gradlew dependencies --no-daemon || true

COPY core core

RUN ./gradlew clean :core:bootstrap:bootJar --no-daemon


# =========================
# STAGE 2: Run
# =========================
FROM eclipse-temurin:21-jre

WORKDIR /app

ENV TZ=Asia/Ho_Chi_Minh
ENV SPRING_PROFILES_ACTIVE=uat

EXPOSE 2509

COPY --from=builder /app/core/bootstrap/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]