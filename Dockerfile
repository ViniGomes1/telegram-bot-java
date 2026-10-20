# Etapa 1: Build da aplicação
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final de execução
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia diretamente o app.jar gerado pelo Maven
COPY --from=build /app/target/app.jar app.jar

ENV PORT=8080
EXPOSE 8080

CMD ["java", "-XX:+UseSerialGC", "-Xmx300m", "-Xms128m", "-jar", "app.jar"]