# Java University Labs 🎓

A collection of Java laboratory works completed during university studies.

## Labs Overview

### Lab 1 — Introduction to Java
Basic Java fundamentals including:
- Primitive data types
- If/else operators
- Switch/case operators
- While loops
- For loops and 2D arrays

### Lab 2 — Exceptions & Collections
- Custom exception `InvalidCredentialsException` for login system simulation
- `LinkedList` as a customer service request queue with FIFO processing

### Lab 3 — Map, Set, Generics & Wildcards
- Student registry using `HashMap`
- Unique elements storage and occurrence counting
- Generic class `Box<T>`
- Generic method `findMax`
- Generic class `Pair<T, U>` with comparison method
- Upper-bounded wildcard for calculating total area of shapes
- Lower-bounded wildcard for adding integers to different list types
- Animal shelter hierarchy with polymorphism

### Lab 4 — Collections, Stream & Optional
Working with Java Streams and Optional including:
- Finding elements with conditions
- Grouping and filtering
- FlatMap operations
- Aggregation functions

### Lab 6 — Multithreading & Concurrency
Variant 4 — Restaurant service model simulation:
- Multiple clients and waiters running as parallel threads
- `BlockingQueue` for thread-safe order management
- `CountDownLatch` for inter-thread communication
- Guaranteed payment before client exit

### Lab 7 — Spring Boot REST API 🏨
Hotel management system — Variant 3:
- Full REST API with CRUD operations for 5 models
- Models: Room, Guest, Booking, Staff, Service
- HTTP methods: GET, POST, PUT, PATCH, DELETE for each model

### Lab 8 — Spring Data JDBC
- Implemented database communication using `JdbcTemplate`
- Custom SQL queries for fetching specific data (e.g., retrieving only available rooms)
- Separation of custom JDBC repositories from standard JPA repositories

### Lab 9 — Advanced Spring Data JPA & Hibernate
- Entity relationships modeling (`@OneToOne`, `@ManyToOne`, `@ManyToMany`)
- Performance optimization: diagnosed and solved the **N+1 select problem** using `@Query` and `JOIN FETCH`
- Disabled `open-in-view` for better database connection management
- Database migration and automated initial data seeding using **Liquibase**
- Implemented API Pagination using `Pageable` and `Page<T>`
- Advanced transaction management via `@Transactional` with appropriate `Propagation` parameters

### Lab 10 — API Documentation (Swagger / OpenAPI)
- Automated REST API documentation using `springdoc-openapi`
- Detailed endpoint descriptions with `@Tag`, `@Operation`, and HTTP status codes via `@ApiResponses`
- Safe data representation: comprehensively documenting `DTOs` via `@Schema` while completely hiding internal `@Entity` database models
- Custom `OpenAPI` bean configuration (project metadata, contact info, hiding internal server URLs)
  
made with 💚 by smilezevil
