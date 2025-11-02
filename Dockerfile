FROM openjdk:17-jdk-slim

COPY target/*.jar Trilnk.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "Trilnk.jar"]