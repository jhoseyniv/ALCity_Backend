FROM openjdk:18-jdk-alpine3.14
ARG JAR_FILE=target/object_service-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} object_service.jar

ENTRYPOINT ["java","-jar","/object_service.jar"]

EXPOSE 8080
