# Etapa 1: Compilación del servicio seleccionado
FROM maven:3.9.4-eclipse-temurin-21-alpine AS build
ARG SERVICE_NAME
WORKDIR /build
COPY ${SERVICE_NAME}/pom.xml ./
COPY ${SERVICE_NAME}/src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

# Instala netcat para espera a MySQL
RUN apk add --no-cache netcat-openbsd

# Copia entrypoint común
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Copia el JAR desde la etapa anterior
ARG SERVICE_NAME
COPY --from=build /build/target/*.jar app.jar

# Variables configurables
ENV JAR_FILE=app.jar
ENV MYSQL_HOST=mysql
ENV MYSQL_PORT=3306

EXPOSE 8080

ENTRYPOINT ["/bin/sh", "/entrypoint.sh"]
