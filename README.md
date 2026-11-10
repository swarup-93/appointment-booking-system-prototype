# Appointment Booking System – Prototype

A prototype implementation of a Hospital Appointment Booking System built to explore backend architecture, REST API development, authentication, authorization, and appointment management workflows.

> **Disclaimer:** This repository contains a prototype implementation. The APIs, endpoints, data models, and workflows demonstrated here do not represent or document the actual production API.

## Overview

The Appointment Booking System is designed to simplify the process of managing hospital appointments by providing separate functionalities for patients, doctors, and administrators.

The project focuses on implementing backend development concepts using Spring Boot, with an emphasis on clean architecture, secure authentication, database management, and RESTful API design.

## Tech Stack

| Technology        | Purpose                          |
| ----------------- | -------------------------------- |
| Java 21           | Programming language             |
| Spring Boot       | Backend framework                |
| Spring Security   | Authentication and authorization |
| Spring Data JPA   | Database operations              |
| MySQL             | Relational database              |
| Flyway            | Database migrations              |
| JWT               | Token-based authentication       |
| OpenAPI / Swagger | API documentation                |

## Core Features

- User registration and authentication
- JWT-based authentication
- Role-based access control
- Patient management
- Doctor management
- Doctor approval workflow
- Appointment management
- Account status management
- Request validation and exception handling
- API documentation using OpenAPI

## User Roles

The system is designed around three primary roles:

| Role       | Responsibilities                                                |
|------------|-----------------------------------------------------------------|
| PATIENT    | Manage patient profile and appointments                         |
| DOCTOR     | Manage doctor profile and appointments                          |
| ADMIN      | Manage users, doctors, approvals, and day-to-day system operations |
| SUPERADMIN | Manage administrators, system-wide configurations, and all administrative operations |

## Project Structure

The application follows a layered architecture to separate responsibilities and maintainability.

- `controller` – REST API endpoints
- `service` – Business logic
- `repository` – Database access
- `entity` – JPA entities
- `dto` – Request and response objects
- `config` – Application and security configuration
- `exception` – Custom exception handling
- `enums` – Application enumerations

## Getting Started

### Prerequisites

- Java 21 or later
- Maven
- MySQL
- Git

### Clone the Repository

```bash
git clone <repository-url>
cd appointment-booking-system-prototype
```

### Configure Environment Variables

Configure your database credentials and connection details using environment variables.

```properties
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
```

### Run the Application

```bash
./mvnw spring-boot:run
```

For Windows:

```bash
mvnw.cmd spring-boot:run
```

## API Documentation

The application uses OpenAPI and Swagger UI to provide interactive API documentation.

Once the application is running, access:

```text
http://localhost:8080/swagger-ui/index.html
```

The documentation allows developers to explore available endpoints, inspect request and response structures, and test API operations.

## Project Status

**Status:** `Prototype`

This project is currently a prototype and is intended for development, experimentation, and architectural exploration.

The implementation and API specifications may change as development progresses.


Copyright (c) 2026 Swarup Dhavan. All Rights Reserved.

# Proprietary License

This repository and all its contents, including source code, documentation, designs, and associated materials, are the intellectual property of Swarup Dhavan.

This project is made publicly available solely for educational, learning, and reference purposes.

No permission is granted to copy, modify, reproduce, redistribute, republish, sublicense, or incorporate any part of this project into another project, product, or service, whether commercial or non-commercial, without prior written permission from the copyright holder.

In particular, you may not:

- Claim this project or any part of it as your own work.
- Rebrand, republish, or present this project as your own product.
- Incorporate the source code into your own applications or commercial products.
- Distribute modified or unmodified copies of this project.
- Use this project as the foundation of a competing or independently distributed product without written permission.

Viewing the source code for personal learning and educational reference is permitted, subject to applicable law and GitHub's terms of service.

This project is provided "AS IS", without warranties or guarantees of any kind. It is a prototype and is not intended for production use.

For licensing inquiries or requests for permission, contact the copyright holder.

All rights not expressly granted are reserved.
