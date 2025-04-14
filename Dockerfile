# Etapa 1: build da aplicação
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copia os arquivos de build e wrapper
COPY gradlew build.gradle.kts settings.gradle.kts ./
COPY gradle ./gradle

# Copia o restante do código
COPY . .

# Dá permissão de execução ao wrapper e compila o projeto
RUN chmod +x gradlew && ./gradlew assemble --no-daemon

# Etapa 2: imagem final para execução
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia o jar gerado na etapa anterior
COPY --from=build /app/build/libs/*.jar app.jar

# Expõe a porta padrão do Spring Boot
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]