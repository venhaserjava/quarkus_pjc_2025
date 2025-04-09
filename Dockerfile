# Etapa final - apenas a aplicação empacotada (JVM runner)
FROM eclipse-temurin:21-jdk as runner

WORKDIR /work/

# Copia a aplicação empacotada
COPY target/quarkus-app/lib/ /work/lib/
COPY target/quarkus-app/*.jar /work/
COPY target/quarkus-app/app/ /work/app/
COPY target/quarkus-app/quarkus/ /work/quarkus/

# Exponha a porta padrão do Quarkus
EXPOSE 8080

# Executa a aplicação
CMD ["java", "-jar", "app.jar"]
