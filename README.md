# papirfly-task
Papirfly hiring task 2024

## Tech stack

- Kotlin, SpringBoot, Spring JPA,
- Postgres, Flyway, Docker compose
- JUnit, Kotest, Mockk

## Done

1. Domain entities with Flyway migration scripts for DDL
2. REST endpoints with Swagger (see `application.yml`)
3. Structured architecture using CQRS (or rather tried to :) )
4. Unit tested some relevant happy paths and edge cases

### Possible improvements

- Exception
  - create ApiException for detailed messages about exception using `@ControllerAdvice`
    - also Validation violation exceptions could be remapped into pretty response
- Domain
  - instead of using `UUID` as `@Id`, use `Long` as `@Id` and use the `UUID` simply for `equals+hashcode` purposes
  - Use `jOOQ` for Search queries instead of Spring JPA Specification
- Tests
  - Create Integration tests with Postgres test container