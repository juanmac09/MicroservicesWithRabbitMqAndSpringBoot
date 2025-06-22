# Etapa 1: Compilación
FROM maven:3.9.4-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copiar el entrypoint común
COPY ../entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Copiar JAR
ARG JAR_FILE=order-service.jar
COPY --from=build /app/target/*.jar ${JAR_FILE}

# Variables necesarias
ENV JAR_FILE=${JAR_FILE}
ENV MYSQL_HOST=mysql
ENV MYSQL_PORT=3306

EXPOSE 8080

ENTRYPOINT ["/entrypoint.sh"]
