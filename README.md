# CloudCart Product Service

Product microservice for the CloudCart E-commerce platform.

## Overview

CloudCart Product Service manages product lifecycle operations including creation, retrieval, updating, and deletion of products.

The service is built using Spring Boot and follows a layered architecture with Controller, Service, Repository, and Database layers.

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Docker
- Azure

## Features

- Product CRUD APIs
- DTO-based request and response handling
- Bean validation
- Global exception handling
- PostgreSQL persistence
- SKU uniqueness validation
- Idempotency-Key support for duplicate request prevention

## Architecture

```
Client
  |
  |
Product Controller
  |
Product Service
  |
Product Repository
  |
PostgreSQL Database
```

## API Endpoints

### Create Product

```
POST /products
```

Headers:

```
idempotencyKey: <unique-key>
```

Example Request:

```json
{
  "sku": "IPHONE-15-128-BLK",
  "name": "iPhone 15",
  "category": "ELECTRONICS",
  "price": 69999.00,
  "description": "Apple iPhone 15 with 128GB storage"
}
```

Example Response:

```json
{
  "id": "5920a710-9589-47bb-a581-d572183b8640",
  "sku": "IPHONE-15-128-BLK",
  "name": "iPhone 15",
  "category": "ELECTRONICS",
  "price": 69999.00,
  "description": "Apple iPhone 15 with 128GB storage"
}
```

---

### Get All Products

```
GET /products
```

---

### Get Product By ID

```
GET /products/{id}
```

---

### Update Product

```
PUT /products/{id}
```

---

### Delete Product

```
DELETE /products/{id}
```

---

# Local Setup

## Prerequisites

- Java 21
- Maven
- Docker
- PostgreSQL

---

## Database Setup

Start PostgreSQL using Docker.

Example configuration:

```
Database:
cloudcart_product_db

Username:
cloudcart

Password:
cloudcart_dev_password

Port:
5407
```

---

## Application Configuration

Create a local configuration file:

```
src/main/resources/application-local.properties
```

Add:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5407/cloudcart_product_db
spring.datasource.username=cloudcart
spring.datasource.password=cloudcart_dev_password
```

---

## Run Application

Using Maven:

```bash
./mvnw spring-boot:run
```

Application starts on:

```
http://localhost:8080
```

---

# Docker

## Build Application

First create the Spring Boot JAR:

```bash
./mvnw clean package
```

---

## Build Docker Image

```bash
docker build -t cloudcart-product-service .
```

---

## Run Docker Container

```bash
docker run -p 8080:8080 cloudcart-product-service
```

---

# Project Structure

```
src/main/java/com/cloudcart/product

├── controller
│
├── service
│
├── repository
│
├── model
│
├── dto
│
├── mapper
│
└── exceptions
```

---

# Future Enhancements

- Authentication Service integration
- API Gateway integration
- Order Service integration
- Azure deployment
- GitHub Actions CI/CD pipeline
- Kubernetes deployment