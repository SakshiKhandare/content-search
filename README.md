# Content Search Service

A Java-based backend service for searching and retrieving content with filtering, pagination, and efficient query handling.
Built using Spring Boot with a clean, layered architecture and relational persistence.

## Short Description
A RESTful service that enables querying content records based on search criteria.
Focuses on structured data access, pagination, and scalable read operations.

## Tech Stack
- Language: Java 17
- Frameworks: Spring Boot, Spring Web, Spring Data JPA, Hibernate
- Build Tool: Maven
- Database: PostgreSQL

## Key Features
- Search content records using query parameters
- Retrieve content by unique identifier
- Pagination support for large result sets
- JPA-based persistence with optimized queries
- Clear separation of controller, service, and repository layers
- Consistent request and response models

## API Endpoints

Create Content

POST /api/contents

Request Body:
    { "title": "Spring Boot Guide", "body": "Introductory content", "category": "TECH" }

Response:
    201 Created

---

Get Content by ID

GET /api/contents/{id}

Response:
    200 OK
    404 Not Found

---

Search Contents (with pagination and filters)

GET /api/contents/search?query=spring&category=TECH&page=0&size=10

Query Params:
    query = search keyword
    category = optional category filter
    page = default 0
    size = default 10

Response:
    Paged list of contents

---

Get All Contents

GET /api/contents?page=0&size=10

Query Params:
    page = default 0
    size = default 10

Response:
    Paged list of contents

## Getting Started

Prerequisites
- Java 17 or higher
- Maven
- PostgreSQL instance

Run Instructions
- Configure database connection in application.properties
- Build the project using `mvn clean install`
- Run the application using `mvn spring-boot:run`
- Service runs on port 8080

## Project Structure

    src/main/java
    └── com.example.contentsearch
        ├── ContentSearchApplication.java
        │   -> Spring Boot application entry point
        │
        ├── controller
        │   └── ContentController.java
        │       -> REST API endpoints for content creation, retrieval, and search
        │
        ├── service
        │   └── ContentService.java
        │       -> Business logic for content operations and search filters
        │
        ├── repository
        │   └── ContentRepository.java
        │       -> JPA repository for CRUD and search queries
        │
        ├── entity
        │   └── Content.java
        │       -> JPA entity representing a content record
        │
        └── dto
            ├── CreateContentRequest.java
            │   -> Request model for creating a new content
            ├── ContentResponse.java
            │   -> Response model representing content data
            └── SearchContentResponse.java
                -> Paginated search response model (if implemented)

    src/main/resources
    └── application.properties
        -> Configuration (datasource, server, JPA settings)


## Why This Project Matters
The service demonstrates backend patterns for read-heavy systems, including filtered search, pagination, and efficient data access, while maintaining clean architecture and maintainable code structure.
