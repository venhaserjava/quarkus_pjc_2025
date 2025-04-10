#!/bin/bash

echo "🔎 Verificando containers antigos..."
docker rm -f postgres_db minio_storage quarkus_pjc_2025_dev 2>/dev/null

echo "🔄 Subindo PostgreSQL e MinIO..."
docker compose up -d postgres minio

# Esperar até que o banco esteja aceitando conexões
echo "⏳ Aguardando PostgreSQL ficar disponível..."
until docker exec postgres_db pg_isready -U postgres > /dev/null 2>&1; do
  echo "🔄 Aguardando banco..."
  sleep 2
done

echo "✅ Banco pronto!"
echo "📦 Subindo aplicação em modo desenvolvimento (quarkus:dev)..."
docker compose up dev-app
