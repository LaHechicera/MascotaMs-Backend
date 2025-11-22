# ============================
# 🟦 Etapa 1 — Build con Maven (JDK 17)
# ============================
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Directorio de trabajo dentro del contenedor
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 🟩 Etapa 2 — Runtime con JDK 17 ligero
FROM eclipse-temurin:17-jdk-jammy

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
