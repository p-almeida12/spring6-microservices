FROM eclipse-temurin:17-jdk-jammy
ARG JAR_FILE=target/*.jar
COPY ./target/currency-exchange-service-0.0.1.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
