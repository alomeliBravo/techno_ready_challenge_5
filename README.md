# techno_ready_challenge_5
MELI - e-commerce | Challenge 5: Spring and Spring Boot in Java for Web Applications 

API for managing customers, items, and orders. Developed in Spring Boot. 

![Java](https://img.shields.io/badge/Java-17-blue) 
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)

[CHANGELOG](CHANGELOG.md)

## Table of content

- [Description](#description)
- [Technologies](#technologies-used)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [How to run](#how-to-run)
- [Validation and error handling](#validation-and-error-handling)
- [Architecture](#architecture)
- [Endpoints](#endpoints)

## Description

Meli E-commerce is an API built with Spring Boot for managing customers, items, and orders.
The API provides full CRUD operations through RESTful endpoints, enabling efficient data interaction.

Data is stored in a relational database depending on the active profile:

- Test / Development: uses an in-memory H2 database.
- Production: uses PostgreSQL for data persistence.

## Technologies used

- Java 17
- Spring Boot 3.5.6
- Maven
- PostgreSQL Database
- H2 Database
- Lombok
- Jakarta Validation

## Prerequisites

- Java 17 or Higher
- Spring Boot 3.5.6
- Maven 3.9 or Higher
- Docker to run Database locally if needed

## Installation

1. Clone the repository

```bash
git clone https://github.com/alomeliBravo/techno_ready_challenge_5.git
```

2. Compile the project

```bash
mvn clean install
```

## How to run

**Setup the environment**

Rename .env.example -> .env
```bash
mv .env.example .env
```

Configure .env file

**Example**
```.env
DB_URL=jdbc:postgresql://db.XXXXXXXXXX.supabase.co:5432/postgres
DB_USERNAME=postgres
DB_PASSWORD=XXXXX
```

You will see 4 different application.properties

- application.properties
- application-dev.properties (develompent profile)
- application-prod.properties (production profile)
- application-test.properties (test profile)

You can run the project in different ways

**Maven**
```bash
mvn spring-boot:run #dev profile (default)
```
**CMD**
```bash
run.bat dev  #development profile
run.bat test #test profile
run.bat prod #production profile
run.bat      #development profile (default)
```
**PowerShell**
```PowerShell
.\run.ps1 dev  #development profile
.\run.ps1 test #test profile
.\run.ps1 prod #production profile
.\run.ps1      #development profile (default)
```

**MAC / LINUX**

```bash
chmod +x run.sh #Give permission to execute

./run.sh dev    #development profile
./run.sh test   #test profile
./run.sh prod   #production profile
./run.sh        #development profile
```

## Validation and Error Handling

The API uses **Jakarta Validation** annotations to ensure data integrity across all DTOs.

### Examples

#### Customer validation example
```json
{
  "name": "",
  "age": -1,
  "email": "",
  "address": ""
}
```

```json
{
  "status": 400,
  "errorMessage": "name: name is required | age: age must be greater than zero | email: email is required",
  "path": "/api/v1/clients/",
  "timestamp": "2025-10-21"
}
```

**Global error handling**

All validation and business logic error are handled by a GlobalExceptionHandler, returning a consistent JSON reponses:

| Exception Type        | Https Status | Example Message         |
|-----------------------|--------------|-------------------------|
| NotFoundException     | 404          | No item found with id 1 |
| MethodArgumentInvalid | 400          | email is required       |
| GenericException      | 500          | Sorry. An unexpected error has ocurred. Try again later                        |

## Architecture

This project follows a layered architecture pattern for clear separation of concerns:

```mathematica
Controller -> Service -> Repository -> Entity
```

**Layers**

- **Controllers**: Exposes REST endpoints and hanldes HTTP request/responses.
- **Service**: Contains the business logic and orchestrates the workflow.
- **Repository**: Handles persistence with JPA/Hibernate.
- **Entity**: Defines database models mapped to relational tables.
- **DTOs (Data Transfer Objects)**: Facilitate safe data exchange between layers.

This design ensures high maintanability, scalability and testability.

```md
techno_ready_challenge_5/
└── src/
└── main/
├── java/
│   └── com/
│       └── pikolic/
│           └── meli/
│               ├── controller/
│               │   ├── ClientController.java
│               │   ├── ClientOrderController.java
│               │   ├── ItemController.java
│               │   └── OrderController.java
│               │
│               ├── dto/
│               │   ├── client/
│               │   │   ├── ClientCreateDTO.java
│               │   │   ├── ClientResponseDTO.java
│               │   │   └── ClientUpdateDTO.java
│               │   ├── item/
│               │   │   ├── ItemCreateDTO.java
│               │   │   ├── ItemResponseDTO.java
│               │   │   └── ItemUpdateDTO.java
│               │   └── order/
│               │       ├── OrderCreateDTO.java
│               │       ├── OrderCreateForClientDTO.java
│               │       ├── OrderResponseDTO.java
│               │       └── OrderUpdateDTO.java
│               │
│               ├── entity/
│               │   ├── ClientEntity.java
│               │   ├── ItemEntity.java
│               │   └── OrderEntity.java
│               │
│               ├── exception/
│               │   ├── ApiExceptionBase.java
│               │   ├── ErrorResponse.java
│               │   ├── ForbbidenException.java
│               │   ├── GlobalExceptionHandler.java
│               │   └── NotFoundException.java
│               │
│               ├── mapper/
│               │   ├── ClientMapper.java
│               │   ├── ItemMapper.java
│               │   └── OrderMapper.java
│               │
│               ├── repository/
│               │   ├── ClientRepository.java
│               │   ├── ItemRepository.java
│               │   └── OrderRepository.java
│               │
│               └── service/
│               │   ├── impl/
│               │   │   ├── ClientOrderServiceImpl.java
│               │   │   ├── ClientServiceImpl.java
│               │   │   ├── ItemServiceImpl.java
│               │   │   └── OrderServiceImpl.java
│               │   │
│               │   ├── ClientOrderService.java
│               │   ├── ClientService.java
│               │   ├── ItemService.java
│               │   └── OrderService.java
│               │
│               └── MeliApplication.java
│
└── resources/
    └── application.properties
```

## Endpoints

**Client Endpoints**

| Method | Endpoint             | Description           |
|--------|----------------------|-----------------------|
| POST   | /api/v1/clients/     | Create a new Client   |
| GET    | /api/v1/clients/     | Get all Clients       |
| GET    | /api/v1/clients/{id} | Get a Client by ID    |
| PUT    | /api/v1/clients/{id} | Update a Client by ID |
| DELETE | /api/v1/clients/{id} | Delete a client by ID |

**Ïtem Endpoints**

| Method | Endpoint           | Description         |
|--------|--------------------|---------------------|
| POST   | /api/v1/items/     | Create a new Item   |
| GET    | /api/v1/items/     | Get all Items       |
| GET    | /api/v1/items/{id} | Get a Item by ID    |
| PUT    | /api/v1/items/{id} | Update a Item by ID |
| DELETE | /api/v1/items/{id} | Delete a Item by ID |

**Order Endpoints**

| Method | Endpoint            | Description          |
|--------|---------------------|----------------------|
| POST   | /api/v1/orders/     | Create a new Order   |
| GET    | /api/v1/orders/     | Get all Orders       |
| GET    | /api/v1/orders/{id} | Get a Order by ID    |
| PUT    | /api/v1/orders/{id} | Update a Order by ID |
| DELETE | /api/v1/orders/{id} | Delete a Order by ID |

**Client-Order Enpoints**

| Method | Endpoint                                    | Description                      |
|--------|---------------------------------------------|----------------------------------|
| POST   | /api/v1/clients/{clientId}/orders/          | Create a new Order for a Client  |
| GET    | /api/v1/clients/{clientId}/orders/          | Get all Orders of a Client       |
| GET    | /api/v1/clients/{clientId}/orders/{orderId} | Get a specific Order of a Client |
| PUT    | /api/v1/clients/{clientId}/orders/{orderId} | Update Order of a Client         |
| DELETE | /api/v1/clients/{clientId}/orders/{orderId} | Delete a Order of a Client       |
