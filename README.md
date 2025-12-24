# Digital Services Marketplace (MicroServices)

This project implements a backend system for a Digital Marketplace using Java, Spring Boot, and Spring Cloud. The goal is to model a production-style microservices architecture with clear service separation, externalized configuration, and reliable communication between services.

# Services

**Config Server**
Centralized configuration. Application properties are stored in a GitHub repository and loaded at startup.

**Service Registry (Eureka)**
Provides service discovery. Each service registers itself and discovers others dynamically.

**API Gateway**
Acts as the single entry point for client requests and forwards them to backend services.

**User Service**
Handles user-related operations.

**Order Service**
Creates and manages orders.
Communicates with User and Notification services using OpenFeign.
Implements timeouts, retries, circuit breakers, and fallbacks.
Uses correlation IDs for tracing and structured logging.

**Notification Service**
Receives and processes notification requests.

**Catalog Service**
Provides basic product catalog functionality.

**Observability and Health**

Each service exposes Spring Boot Actuator endpoints such as health, metrics, and info.
Correlation IDs are propagated across services for request tracing.
Order Service uses readiness checks influenced by downstream stability.

# Technology

Java 17
Spring Boot
Spring Cloud
OpenFeign
Resilience4j
Eureka
Maven

# Purpose

This project is being developed to practice real-world backend engineering patterns, focusing on maintainability, resilience, and clarity rather than demo-level examples.