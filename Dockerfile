FROM arm64v8/openjdk:18-jdk
EXPOSE 8080
ADD build/libs/SpringBootRestJava-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]