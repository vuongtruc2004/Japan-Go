# Stage 1: Build application
FROM eclipse-temurin:21-jdk-alpine AS builder

RUN apk add --no-cache gradle

WORKDIR /build

# copy gradle config trước để cache dependency
COPY build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle
COPY gradlew ./

RUN ./gradlew dependencies --no-daemon || true

# copy source
COPY src ./src

# build jar
RUN ./gradlew build -x test --no-daemon


# Stage 2: Run application
FROM eclipse-temurin:21-jre-alpine AS runtime

WORKDIR /app

# jar của gradle nằm trong build/libs
COPY --from=builder /build/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]