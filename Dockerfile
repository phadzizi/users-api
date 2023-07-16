FROM adoptopenjdk:11-jdk-hotspot
WORKDIR /app
COPY target/users-api-0.0.1-SNAPSHOT.jar .
COPY src/main/resources/application.properties .
CMD ["java", "-jar", "users-api-0.0.1-SNAPSHOT.jar"]
