# Etapa 1: Build da aplicação usando Maven com JDK 21 (ou troque para 17 se usar Java 17)
FROM maven:3.9-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copia dependências primeiro para aproveitar o cache do Docker
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o código-fonte e gera o jar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final leve (apenas JRE) para economizar RAM
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copia o fat jar gerado na etapa anterior
COPY --from=build /app/target/*-shaded.jar app.jar || COPY --from=build /app/target/*.jar app.jar

# Define porta padrão (o Render sobrescreve isso dinamicamente)
ENV PORT=8080
EXPOSE 8080

# Flags de memória otimizadas para os 512MB do plano gratuito do Render
CMD ["java", "-XX:+UseSerialGC", "-Xmx300m", "-Xms128m", "-jar", "app.jar"]