# Etapa de construção (Build Phase)
# Usamos uma imagem base que já tem o Maven 3.8 e o JDK 17 instalados
FROM maven:3.8-openjdk-17 AS BUILD

# Define o diretório de trabalho dentro do container. Todos os comandos subsequentes serão executados dentro deste diretório.
WORKDIR /app

# Copia todos os arquivos do diretório atual (local) para dentro do container no diretório /app
COPY . .

# Executa o Maven para construir o projeto Java.
# A opção -DskipTests ignora a execução de testes durante a construção.
RUN mvn clean install -DskipTests

# imagem base correta (Java 17)
FROM eclipse-temurin:17-jre-jammy
# define diretório de trabalho dentro do container
WORKDIR /app

# copia o jar gerado para dentro do container (ajustei o caminho e nome)
COPY target/bff.agendador-0.0.1-SNAPSHOT.jar /app/bff-agendador-tarefas.jar

# expõe a porta que a aplicação usa
EXPOSE 8084

# comando para rodar o jar
CMD ["java", "-jar", "/app/bff-agendador-tarefas.jar"]
