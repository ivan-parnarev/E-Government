# Build Stage
FROM maven:3.8.4-openjdk-17-slim AS builder

WORKDIR /app

COPY ../../main/pom.xml .
COPY ../../main/src src/

RUN mvn clean install -DskipTests

# Runtime Stage
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]