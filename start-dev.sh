#!/bin/bash

echo "🔄 Subindo PostgreSQL e MinIO..."
docker-compose up -d postgres minio

echo "⏳ Aguardando containers iniciarem..."
sleep 5

echo "✅ Containers prontos!"
echo "📦 Subindo aplicação em modo desenvolvimento (quarkus:dev)..."
docker-compose up dev-app
