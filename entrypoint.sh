#!/bin/sh

echo "Esperando a que MySQL esté disponible en $MYSQL_HOST:$MYSQL_PORT..."
while ! nc -z "$MYSQL_HOST" "$MYSQL_PORT"; do
  sleep 1
done

echo "MySQL está disponible. Iniciando aplicación..."
exec java -jar "$JAR_FILE"
