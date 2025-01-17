FROM openjdk:18-jdk-alpine3.14
ARG JAR_FILE=target/alcity_service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} alcity_service.jar
ENTRYPOINT ["java","-jar","/alcity_service.jar"]
EXPOSE 8080
