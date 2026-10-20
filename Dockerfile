# Etapa 1: Compilação do JAR com Maven
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Runtime com suporte a navegadores (Ubuntu Noble)
# Use a mesma versão minor configurada no seu pom.xml (ex: 1.49.0, 1.48.0, etc.)
FROM mcr.microsoft.com/playwright/java:v1.63.0-noble
WORKDIR /app

# Instala o WebKit explicitamente caso a imagem base venha apenas com Chromium
RUN npx playwright install --with-deps webkit

# Copia o JAR compilado
COPY --from=build /app/target/app.jar app.jar

ENV PORT=8080
EXPOSE 8080

# Flags de memória para a JVM não competir com o WebKit pelos 512MB do Render
CMD ["java", "-XX:+UseSerialGC", "-Xmx256m", "-Xms128m", "-jar", "app.jar"]