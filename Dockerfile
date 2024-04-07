FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/zetta-0.0.1-SNAPSHOT.jar foreign_exchange.jar
ENTRYPOINT ["java", "-jar", "/foreign_exchange.jar"]