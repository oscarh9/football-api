# Football API - Spring Boot 3.5.5 + Java 21 + Docker

RESTful API for managing football clubs, players, and managers, built with Spring Boot 3.5.5, Java 21, and PostgreSQL.  
The API is fully dockerized and ready for technical testing or local development.

---

1. [Requirements](#1-requirements)
2. [Environment Variables](#2-environment-variables)
3. [Running the Project](#3-running-the-project)
    - [Using Docker (recommended)](#a-using-docker-recommended-for-the-technical-test)
    - [Local Development](#b-local-development-without-docker)
4. [Important Notes](#4-important-notes)
5. [Useful Commands](#5-useful-commands)
6. [Example API Usage (Swagger)](#6-example-api-usage-swagger)
7. [Recommended Workflow](#7-recommended-workflow)

---

## 1. Requirements

- Docker 24+
- Docker Compose 2+
- Java 21 (only for local development, not required with Docker)
- Maven (only for local development, not required with Docker)

---

## 2. Environment Variables

### `.env` (Docker Compose)
```bash
  POSTGRES_DB=api_football_db
  POSTGRES_USER=postgres
  POSTGRES_PASSWORD=postgres
  
  ADMIN_USERNAME=admin
  ADMIN_PASSWORD=password
````

### `.env.local` (Local Development)
```bash
  DB_NAME=api_football_db
  DB_USER=postgres
  DB_PASSWORD=postgres
  DB_HOST=localhost
  DB_PORT=5432
  
  ADMIN_USERNAME=admin
  ADMIN_PASSWORD=password
```

### `.env.example`
- Contains placeholders for the variables above, explaining which ones are for Docker and which ones for local development.
- This file can be committed to the repository so others know which variables to configure.

### Authentication (required for secured endpoints)
- Both Docker and local development require admin credentials to be set via environment variables:

```bash
  ADMIN_USERNAME=admin
  ADMIN_PASSWORD=password
```
- These values will be injected into the application at runtime.
- They are required for performing POST, PUT, or DELETE requests.

---

## 3. Running the Project

### A. Using Docker (recommended for the technical test)

```bash
  docker-compose build
  docker-compose up -d
```
- Wait until the PostgreSQL container passes its healthcheck.
- View application logs:
```bash
  docker-compose logs -f app
```
- Swagger UI is available at: http://localhost:8080/swagger-ui/index.html
- PostgreSQL database can be accessed at `localhost:5432` (user: postgres / password: postgres / db: api_football_db).
- To stop the containers:
```bash
  docker-compose down
```
- To restart fresh (no persistence; `data.sql` will be executed again):
```bash
  docker-compose up -d --build
```

### B. Local Development (without Docker)
1. Create `.env.local` with your local credentials.
2. Run the application from IntelliJ or using Maven:
```bash
  mvn spring-boot:run
```
3. Swagger UI: http://localhost:8080/swagger-ui/index.html
4. PostgreSQL database should match the settings in `.env.local`.


### Authentication

- All `GET /api/v1/**` endpoints are public and can be tested without authentication.
- `POST`, `PUT`, and `DELETE` requests require authentication with the admin credentials.

Default credentials (if configured in `.env`):

- Username: `admin`
- Password: `password`

---

### 4. Important Notes

- **Multi-stage Dockerfile:** Builds the project with Maven and creates a lightweight runtime image containing only the JAR.
- **Docker Compose:** Starts the API and PostgreSQL; the application waits for the database to be ready before starting.
- **Data persistence:** No persistent volume in Docker, so each startup runs data.sql to populate the database from scratch.
- **Best practices:** `.env` and `.env.local` are ignored by Git; `.env.example` serves as a guide for required variables.
- **Authentication:** Admin credentials are required for modifying resources. Make sure `ADMIN_USERNAME` and `ADMIN_PASSWORD` are configured in `.env` or `.env.local`.

---

### 5. Useful Commands

```bash
# Build and start containers
  docker-compose up -d --build

  # Follow application logs
  docker-compose logs -f app

  # Stop containers
  docker-compose down
```

---

### 6. Example API Usage (Swagger)

Once the application is running, you can explore and test all endpoints via Swagger UI:

- **URL:** http://localhost:8080/swagger-ui.html
- **Example endpoints:**

    - `/api/clubs` → CRUD operations for clubs
    - `/api/players` → CRUD operations for players
    - `/api/managers` → CRUD operations for managers

> ⚠️ Note: In Swagger UI, click on the **Authorize** button and enter your admin username/password to test secured endpoints (POST, PUT, DELETE).

---

### 7. Recommended Workflow

- **For technical test (Docker):** just run `docker-compose up -d --build` and test via Swagger or Postman.
- **For local development:** create `.env.local`, run with Maven or IntelliJ, and the API will connect to your local PostgreSQL.

---

This setup ensures that the project works out-of-the-box for reviewers and developers, either in a **dockerized environment** or **local development**, following professional standards.