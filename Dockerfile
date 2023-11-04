FROM openjdk:20-jdk-slim
WORKDIR /opt/app
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java",  "-jar", "app.jar"]