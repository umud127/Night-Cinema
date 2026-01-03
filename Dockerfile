# ===============================
# 1️⃣ Build stage
# ===============================
FROM maven:3.9.9-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests


# ===============================
# 2️⃣ Runtime stage
# ===============================
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/target/Night-Cinema-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Dserver.port=${PORT}", "-jar", "app.jar"]