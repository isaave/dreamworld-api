# ---------- Build stage ----------
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copia apenas o pom.xml primeiro (melhor para o cache)
COPY pom.xml .

# Copia a pasta de código-fonte
COPY src src

# Builda o projeto usando o Maven que já vem na imagem (sem o ./mvnw)
RUN mvn -DskipTests clean package

# ---------- Runtime stage ----------
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copia o arquivo .jar gerado no estágio anterior
COPY --from=build /app/target/*.jar app.jar

# O Render define a porta automaticamente
ENV PORT=8080
EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -Dserver.port=$PORT $JAVA_OPTS -jar app.jar"]