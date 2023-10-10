FROM eclipse-temurin:11.0.20.1_1-jdk
COPY build/libs/parcial-arquitectura-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
