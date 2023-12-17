FROM eclipse-temurin:17-jdk-alpine

ARG JAR_FILE=target/*.jar

ADD ${JAR_FILE} app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]