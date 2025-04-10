#!/bin/bash

echo "🧹 Limpando container e volume do PostgreSQL..."

# Tenta parar e remover o container postgres_db, se existir
docker rm -f postgres_db 2>/dev/null && echo "🗑️ Container 'postgres_db' removido." || echo "⚠️ Container 'postgres_db' não encontrado ou já removido."

# Tenta remover o volume do PostgreSQL
docker volume rm quarkus_pjc_2025_postgres_data 2>/dev/null && echo "🗃️ Volume 'quarkus_pjc_2025_postgres_data' removido." || echo "⚠️ Volume não encontrado ou já foi removido."

echo "✅ Limpeza concluída. Pronto para reiniciar com './start-dev.sh'"

