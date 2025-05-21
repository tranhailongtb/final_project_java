FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY ./target/*.jar app.jar
COPY src/main/resources/* config/
ENTRYPOINT ["java", "-jar", "app.jar"]