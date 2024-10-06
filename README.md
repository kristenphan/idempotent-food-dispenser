# Idempotent Food Dispenser

## Project description
What excites Kristen (aka. acceptance criteria)?
- Build something fun: Make Chinsu eat well. 
- Get hands dirty on hardware
- Implement idempotent API with Spring Boot using idempotency key
- Use Redis for caching idempotency keys
- Create CI/CD pipeline with GitHub Actions: can I deploy locally?

## Architecture

![img.png](doc/architecture.png)

## How to run
```shell
cd infra
docker compose up -d
```

```shell
cd ../backend/food-dispenser-api
mvn spring-boot:run
```

Run requests in [requests.http](backend/src/test/resources/requests.http)

```shell
cd infra
docker compose down
```