# Cloud-Native Order Platform

## What this demonstrates

- Java 8 + Spring Boot 3.5
- REST APIs and Microservices-style separation
- PostgreSQL (RDBMS)
- Redis caching
- Apache Kafka event-driven processing
- Docker / Docker Compose
- Kubernetes manifests
- AWS-ready configuration
- CI/CD with GitHub Actions
- JUnit + Mockito tests
- OpenAPI/Swagger
- Actuator health/metrics
- Resilience patterns and centralized error handling
- Performance-friendly batch processing
- AI-assisted development notes

## Architecture

Client -> Order API -> PostgreSQL
                    -> Redis cache
                    -> Kafka -> Notification Consumer

## Prerequisites

- Java 8
- Maven 3.9+
- Docker Desktop

## Run everything

```bash
docker compose up --build
```

API:
- http://localhost:8080
- Swagger: http://localhost:8080/swagger-ui.html
- Health: http://localhost:8080/actuator/health

## Run without Docker

Start PostgreSQL, Redis and Kafka using the supplied compose file, then:

```bash
./mvnw spring-boot:run
```

On Windows:

```powershell
mvnw.cmd spring-boot:run
```

## API examples

Create an order:

```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"customerId":"C1001","productId":"P1001","quantity":2,"unitPrice":49.99}'
```

Get an order:

```bash
curl http://localhost:8080/api/orders/{id}
```

List orders:

```bash
curl http://localhost:8080/api/orders?page=0&size=20
```

Run batch status processing:

```bash
curl -X POST http://localhost:8080/api/orders/batch/process
```
