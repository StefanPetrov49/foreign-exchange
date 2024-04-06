FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/zetta-0.0.1-SNAPSHOT.jar zetta.jar
ENTRYPOINT ["java", "-jar", "/zetta.jar"]