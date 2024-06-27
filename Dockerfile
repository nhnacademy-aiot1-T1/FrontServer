FROM openjdk:11-jre
COPY target/*.jar front.jar
ENTRYPOINT ["java", "-jar", "front.jar"]