# Employee Department Service

Production-style Spring Boot REST API for managing employees and departments.

## Tech stack

- Java 21
- Spring Boot 3
- Spring Web, Spring Data JPA, Validation
- Spring Security OAuth2 Resource Server / JWT
- PostgreSQL
- Flyway database migrations
- Actuator + Prometheus metrics endpoint
- OpenAPI / Swagger UI
- Docker Compose for local PostgreSQL

## Main features

- Department CRUD
- Employee CRUD
- Employee search/filtering by text, status, department, and hire-date range
- Pagination and sorting
- Bulk employee creation with partial error reporting
- DTO validation
- Database-level constraints and indexes
- Optimistic locking through `@Version`
- Centralized error responses
- Security scopes:
  - `ems.read` for GET APIs
  - `ems.write` for write APIs
- Health checks and metrics

## Run locally

### 1. Start PostgreSQL

```bash
docker compose up -d postgres
```

### 2. Build

```bash
./mvnw clean package
```

If Maven Wrapper is not present, use:

```bash
mvn clean package
```

### 3. Run

```bash
DB_URL=jdbc:postgresql://localhost:5432/ems \
DB_USERNAME=ems \
DB_PASSWORD=ems \
JWT_ISSUER_URI=http://localhost:8081/realms/ems \
mvn spring-boot:run
```

For quick local testing without a real identity provider, you can temporarily relax security in `SecurityConfig`, or run with a local Keycloak realm that issues JWT tokens with `ems.read` and `ems.write` scopes.

## API documentation

After starting the app:

```text
http://localhost:8080/swagger-ui.html
```

## Example APIs

Create department:

```bash
curl -X POST http://localhost:8080/api/v1/departments \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{"name":"Engineering","description":"Software team","status":"ACTIVE"}'
```

Create employee:

```bash
curl -X POST http://localhost:8080/api/v1/employees \
  -H "Authorization: Bearer <token>" \
  -H "Content-Type: application/json" \
  -d '{
    "firstName":"Ali",
    "lastName":"Hassan",
    "email":"ali.hassan@example.com",
    "hireDate":"2025-01-10",
    "salary":25000,
    "status":"ACTIVE",
    "jobTitle":"Backend Developer",
    "departmentId":1
  }'
```

Search employees:

```bash
curl "http://localhost:8080/api/v1/employees?q=ali&status=ACTIVE&page=0&size=20&sort=hireDate,desc" \
  -H "Authorization: Bearer <token>"
```

## Observability

Health:

```text
GET /actuator/health
```

Metrics:

```text
GET /actuator/metrics
GET /actuator/prometheus
```

## Design decisions

- Used PostgreSQL instead of an in-memory database for production realism.
- Used Flyway so schema changes are versioned and reproducible.
- Used DTOs instead of exposing entities directly.
- Disabled Open Session in View to avoid hidden lazy-loading queries from controllers.
- Added indexes for common query paths: status, department, and hire date.
- Used optimistic locking to reduce lost-update risk.
- Used OAuth2 Resource Server because production services should delegate authentication to an identity provider.
- Kept controllers thin and business logic inside services.
- Used centralized exception handling for consistent API errors.

## What I would improve next

- Add Testcontainers integration tests with real PostgreSQL.
- Add Keycloak docker-compose profile for local JWT testing.
- Add audit log table for employee salary/status changes.
- Add soft delete if business rules require historical records.
- Add rate limiting at API gateway level.
- Add CSV/Excel import-export endpoints for HR bulk operations.
- Add field-level authorization so only HR/admin roles can view or update salary.
- Add caching for rarely changing department lookups.
