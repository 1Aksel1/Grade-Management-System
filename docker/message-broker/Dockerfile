FROM maven:4.0.0-rc-5-eclipse-temurin-17-alpine AS builder


WORKDIR /app


COPY pom.xml .
RUN mvn dependency:go-offline


COPY src ./src


RUN mvn clean package -DskipTests



FROM eclipse-temurin:17.0.17_10-jdk-alpine-3.23

WORKDIR /app

COPY --from=builder /app/target/message-broker-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 61616

ENTRYPOINT ["java", "-jar", "app.jar"]

