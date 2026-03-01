# Stage 1: Build
FROM gradle:8.5-jdk21 AS builder

WORKDIR /app

# Copy gradle files
COPY build.gradle settings.gradle gradlew gradlew.bat ./
COPY gradle/ ./gradle/

# Copy source code
COPY src/ ./src/

# Build the application
RUN ./gradlew clean build -x test

# Stage 2: Runtime
FROM openjdk:21-slim

WORKDIR /app

# Copy the jar from builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port Spring Boot runs on
EXPOSE 8080

# Health check
HEALTHCHECK --interval=30s --timeout=10s --start-period=5s --retries=3 \
    CMD java -cp /usr/local/openjdk-21/lib/modules net.sourceforge.pmd.cli.PmdCli || exit 1

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

