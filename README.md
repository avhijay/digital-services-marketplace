

# Digital Services Marketplace (Microservices)

This project implements a backend system for a digital marketplace using Java, Spring Boot, and Spring Cloud.
It is designed to model a production-style microservices architecture with clear service boundaries, explicit data ownership, and resilient inter-service communication.

The focus is on correctness, maintainability, and realistic backend patterns rather than demo-level examples.

## Services

**Config Server**  
Centralized configuration management. Service configuration is stored in a GitHub repository and loaded at startup.

**Service Registry (Eureka)**  
Provides service discovery so services can locate and communicate with each other dynamically.

**API Gateway**  
Acts as a single entry point for client requests and routes them to backend services.

**User Service**  
Owns user data and user lifecycle. Other services access user data only through exposed APIs.

**Catalog Service**  
Owns product data, pricing, currency, and stock levels.  
Stock updates use optimistic locking to prevent lost updates under concurrent access.

**Order Service**  
Coordinates order creation and management.
- Validates users via User Service
- Validates product availability and pricing via Catalog Service
- Persists orders and order items locally
- Updates product stock after successful order creation

Order Service uses OpenFeign for inter-service calls and applies timeouts, retries, circuit breakers, and fallbacks to protect itself from downstream failures.

**Notification Service**  
Receives notification requests (e.g., order events) and processes them asynchronously(Currently - not active )

## Data and Consistency Model

Each service owns its own database.
Cross-service operations are coordinated through API calls rather than shared transactions.

Order creation follows a request–validate–persist–update pattern.
 cross-service interactions rely on consistency and failure handling.

## Observability and Health

- Each service exposes Spring Boot Actuator endpoints (health, metrics, info)
- Correlation IDs are propagated across services for request tracing
- Order Service readiness is influenced by downstream availability

## Technology Stack

- Java 17
- Spring Boot
- Spring Cloud
- OpenFeign
- Resilience4j
- Eureka
- Flyway
- Maven

## Project Status

This project is under active development and is used to practice real-world backend engineering patterns, including service orchestration, fault tolerance, and schema evolution.
