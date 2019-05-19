# Instruções para executar

Fiz o exercício em Kotlin mas como estou utilizando Gradle só é preciso ter Java instalado na máquina,
ou docker e docker-compose

# Instruções para executar

## Utilizando docker (docker-compose)

- Faça o build do projeto
```bash
./gradlew build
```
- Utilize docker compose para configurar os containers. A aplicação estará disponível em localhost:8080
```bash
docker-compose -d up --build
```

## Manualmente (necessário ter rabbitmq instalado na máquina)
```bash
./gradlew bootRun
```
