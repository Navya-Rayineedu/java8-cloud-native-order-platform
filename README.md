# Cloud-Native Order Platform

A portfolio/interview project designed for a Java Technical Analyst / Senior Java Backend role.

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

The project intentionally keeps services in one repository so it is easy to run locally and explain in interviews. The boundaries are designed like independently deployable services.

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

## Interview talking points

1. Why Redis? Reduce repeated database reads for frequently requested orders.
2. Why Kafka? Decouple order creation from downstream notification processing.
3. Why PostgreSQL? Strong transactional consistency for order data.
4. How would you scale? Stateless API replicas behind a load balancer; Kafka consumer groups; Redis; DB read replicas.
5. How would you make it highly available? Multi-AZ deployment, replicated database, replicated Kafka, multiple API/consumer pods, readiness/liveness probes.
6. What is the cache strategy? Cache-aside with eviction/update after writes.
7. What happens if Kafka is unavailable? The transaction remains in the database; event publishing can be moved to an outbox pattern in a production implementation.
8. What would you improve next? Outbox pattern, Kubernetes HPA, distributed tracing, Testcontainers, security with OAuth2/JWT, and AWS deployment.

## Security review agent

This repository now includes a security-focused review workflow that runs on pull requests and pushes to main. The workflow checks for common security issues with Semgrep and scans dependencies for known CVEs with OWASP Dependency-Check.

The review guidance for Copilot-style coding agents lives in [.github/copilot-instructions.md](.github/copilot-instructions.md). The automation is defined in [.github/workflows/security-review.yml](.github/workflows/security-review.yml).

## Important

This is a portfolio/interview project, not a production-certified platform. The README deliberately calls out production improvements so you can discuss trade-offs honestly.
