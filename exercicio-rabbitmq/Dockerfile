FROM openjdk:8-jdk-alpine
EXPOSE 8080
EXPOSE 5672
ADD /build/libs/exercicio-rabbitmq-0.0.1-SNAPSHOT.jar exercicio-rabbitmq.jar
ENTRYPOINT ["java", "-jar", "exercicio-rabbitmq.jar"]