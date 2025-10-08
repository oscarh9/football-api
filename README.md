# Football API - Spring Boot 3.5.5 + Java 21 + Docker


RESTful API for managing football clubs, players, and managers — built with **Spring Boot 3.5.5**, **Java 21**, and **PostgreSQL**.  
The project is fully **dockerized**, so you can run it instantly for local development or technical testing.

---

1. [Requirements](#1-requirements)
2. [Environment Variables](#2-environment-variables)
3. [Running the Project](#3-running-the-project)
    - [Using Docker (recommended)](#a-using-docker-recommended)
    - [Local Development (without Docker)](#b-local-development-without-docker)
4. [Authentication](#4-authentication)
5. [Project Notes](#5-project-notes)
6. [Useful Commands](#6-useful-commands)
7. [Swagger API Docs](#7-swagger-api-docs)
8. [Recommended Workflow](#8-recommended-workflow)

---

## 1. Requirements

- **Docker** ≥ 24
- **Docker Compose** ≥ 2
- **Java 21** (only if running locally)
- **Maven** (only if running locally)

---

## 2. Environment Variables

### `.env` (used by Docker Compose)
```bash
  POSTGRES_DB=api_football_db
  POSTGRES_USER=postgres
  POSTGRES_PASSWORD=postgres
  
  ADMIN_USERNAME=admin
  ADMIN_PASSWORD=password
```

### `.env.example`
- Serves as a public template for required environment variables.
- Copy it before running the project.
> Note: ⚠️ .env is not committed to Git — it holds your private configuration.

---

## 3. Running the Project

### A. Using Docker (recommended)

- Copy the environment file:
```bash
   cp .env.example .env
```

- Edit .env and replace the placeholders (<db_name>, <user>, <password>, etc.) with your actual environment values.

- Build and start the containers:
```bash
  docker-compose up -d --build
```
- Wait until the PostgreSQL container passes its healthcheck.

- View application logs:
```bash
  docker-compose logs -f app
```
- Swagger UI is available at: http://localhost:8080/swagger-ui/index.html
- PostgreSQL database can be accessed at `localhost:5432`.
  - user: `postgres`
  - password: `postgres`
  - db: `api_football_db`

- To stop the containers:
```bash
  docker-compose down
```

- To restart fresh (no persistence; `data.sql` will be executed again):
```bash
  docker-compose up -d --build
```

### B. Local Development (without Docker)
- Ensure PostgreSQL is running locally.
- Add your environment variables directly in IntelliJ:
```bash
  DB_NAME=api_football_db
  DB_USER=postgres
  DB_PASSWORD=postgres
  DB_HOST=localhost
  DB_PORT=5432
  ADMIN_USERNAME=admin
  ADMIN_PASSWORD=admin123
```
- Run the application:
```bash
  mvn spring-boot:run
```
- Swagger UI is available at: http://localhost:8080/swagger-ui/index.html

---

### 4. Authentication

- All `GET /api/v1/**` endpoints are public and can be tested without authentication.
- `POST`, `PUT`, and `DELETE` requests require authentication with the admin credentials.

Default credentials (if configured in `.env`):

- Username: `admin`
- Password: `password`

>In Swagger UI, click Authorize and enter these credentials to test secured endpoints.

---

### 5. Project Notes

- **Multi-stage Dockerfile →** Builds and runs lightweight JAR image.
- **Docker Compose →** Starts app + PostgreSQL with proper dependency checks.
- **data.sql** auto-loads demo data each time containers start (no persistence).
- **Authentication →** Admin credentials injected via environment variables.
- **.env.example →** Template for required environment variables.

---

### 6. Useful Commands

```bash
# Build and start containers
  docker-compose up -d --build

  # Follow application logs
  docker-compose logs -f app

  # Stop containers
  docker-compose down
```

---

### 7. Swagger API Docs

Once the application is running, you can explore and test all endpoints via Swagger UI:

- **URL:** http://localhost:8080/swagger-ui/index.html
- **Example endpoints:**

    - `/api/v1/clubs` → CRUD operations for clubs
    - `/api/v1/players` → CRUD operations for players
    - `/api/v1/managers` → CRUD operations for managers

> ⚠️ Note: In Swagger UI, click on the **Authorize** button and enter your admin username/password to test secured endpoints (POST, PUT, DELETE).

---

### 8. Recommended Workflow

- **For technical test (Docker):** just run `docker-compose up -d --build` and test via Swagger or Postman.
- **For local development:** Start PostgreSQL locally, set .env, and run `mvn spring-boot:run`.

---

With this setup, the project is **ready to run anywhere** — either fully dockerized or locally — following best practices for Spring Boot apps.