# Estágio de Build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# Estágio de Execução
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /app/target/app.jar app.jar

# O Render injeta a variável PORT em tempo de execução
ENV PORT=8080
EXPOSE ${PORT}

ENTRYPOINT ["java", "-jar", "app.jar"]