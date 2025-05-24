FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY target/*.jar app.jar
COPY src/main/resources/application-docker.properties ./config/application.properties
ENTRYPOINT ["java", "-jar", "app.jar"]