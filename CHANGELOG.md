# 📄 CHANGELOG

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),  
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

---

## [1.0.0] - 2025-10-21
### 🎉 Initial Release

#### Added
- **Layered architecture implementation**:
    - `Controller` layer to handle HTTP requests and expose REST endpoints.
    - `Service` layer containing business logic.
    - `Repository` layer for persistence using Spring Data JPA.
- **DTOs** for all main entities:
    - `ClientCreateDTO`, `ClientUpdateDTO`, `ClientResponseDTO`
    - `ItemCreateDTO`, `ItemUpdateDTO`, `ItemResponseDTO`
    - `OrderCreateDTO`, `OrderUpdateDTO`, `OrderResponseDTO`
- **Entities**:
    - `ClientEntity`, `ItemEntity`, `OrderEntity`
- **Mappers** for entity ↔ DTO conversions:
    - `ClientMapper`, `ItemMapper`, `OrderMapper`
- **Services**:
    - `ClientService`, `ItemService`, `OrderService`, and `ClientOrderService`
- **Global exception handling**:
    - Centralized error management through `GlobalExceptionHandler`
    - Custom exceptions (`NotFoundException`, `ForbiddenException`, etc.)
    - Standardized JSON error responses
- **Database integration**:
    - Embedded **H2** database for testing and development
    - Profile-based configuration (`application.properties`)
- **Project documentation**:
    - `README.md` detailing installation, architecture, validation, and endpoints

---

## [Unreleased]
### 🔧 Planned
- Add **PostgreSQL** configuration for production environment.
- Integrate **Swagger / OpenAPI** documentation for endpoints.
- Implement **pagination and sorting** in listing endpoints.
- Add **unit and integration tests** for services and controllers.
- Add **Docker Compose** setup for local PostgreSQL and application startup.
