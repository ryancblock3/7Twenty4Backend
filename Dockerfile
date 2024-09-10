FROM openjdk:17-slim
WORKDIR /app
COPY target/*.jar app.jar
COPY src/main/resources/application.properties ./
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]