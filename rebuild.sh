#!/bin/bash

echo "🛑 Parando containers..."
docker compose down

echo "🧹 Removendo diretório 'target/' manualmente..."
rm -rf target/

echo "🔨 Limpando e reconstruindo projeto com Maven..."
./mvnw clean install

if [ $? -eq 0 ]; then
  echo "✅ Build finalizado com sucesso!"
  echo "🚀 Subindo ambiente em modo desenvolvimento..."
  ./start-dev.sh
else
  echo "❌ Erro durante a build. Corrija e tente novamente."
fi
