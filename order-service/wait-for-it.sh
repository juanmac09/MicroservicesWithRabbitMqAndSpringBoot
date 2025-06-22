#!/bin/sh

HOST="$1"
PORT="$2"
shift 2

echo "Esperando a que $HOST:$PORT esté disponible..."

while ! nc -z "$HOST" "$PORT"; do
  sleep 1
done

echo "$HOST:$PORT está disponible. Ejecutando el comando..."

exec "$@"
