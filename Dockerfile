# Etapa 1: Compilación del proyecto Spring Boot
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app

# Cachear dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copiar código fuente y compilar
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen de ejecución ligera
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiar el .jar compilado
COPY --from=builder /app/target/*.jar app.jar

# Exponer el puerto para Catálogo
EXPOSE 8082

# Ejecutar el microservicio
ENTRYPOINT ["java", "-jar", "app.jar"]