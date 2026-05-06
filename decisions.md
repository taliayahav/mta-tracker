# Architecture & Design Decisions

This file tracks every meaningful technical decision made during the build of the NYC Subway Live Companion.

---

## Decision: Layered Architecture (Controller → Service → Repository → External API)
**Why:**
Each layer has one job. Controllers handle HTTP. Services handle business logic. Repositories handle database access. The external API layer handles talking to the MTA. If any one layer changes, the others don't need to.

**Alternatives:**
Put all logic in controllers

**Tradeoffs:**
More classes and files upfront, but every layer is independently testable and replaceable. This is standard in production Java backends.

---

## Decision: PostgreSQL as the primary database

**Why:**
Chose PostgreSQL because I need to store user facing data like saved routes and favorite stations. PostgreSQL works  well with Spring Data JPA and is a solid, reliable choice for relational data, and it’s widely used in real world applications.

**Alternatives:**
Considered MySQL (very similar) and MongoDB, but MongoDB is a document database and doesn’t fit as naturally with relational data like routes and stops.

**Tradeoffs:**
Requires running a local Postgres instance, which adds more setup. Still worthwhile to build with tools and patterns that are closer to production.


---

## Decision: Spring Boot as the backend framework
**Why:**
Spring Boot simplifies backend development by taking care of common setup and wiring, allowing me to focus on application logic rather than boilerplate configuration. It wires together a web server, database connection, dependency injection, and more with minimal setup. It is the dominant Java web framework in industry.

**Alternatives:**
Quarkus (faster startup, smaller footprint), plain Spring MVC (more manual configuration).

**Tradeoffs:**
Heavier than alternatives, but the ecosystem, documentation, and job-market relevance are unmatched.

---

## Decision: Entity named Stop (not Station)
**Why:**
I chose “Stop” because that’s the terminology used in the MTA GTFS data model. Each stop actually represents a specific platform/direction (for example, “127N” for northbound and “127S” for southbound). Keeping the same naming as the source data makes it easier to reason about and reduces confusion when working with the raw feed.

**Alternatives:**
I considered using “Station” since it’s more natural to speaking language, but it hides the fact that a single station can have multiple directional stops. “Platform” was another option, but it feels too detailed for this phase.

**Tradeoffs:**
It’s slightly less intuitive from a user perspective, but it’s more accurate to the underlying data structure. We can always map “Stop” to “Station” later in DTOs or API responses to make it more user-friendly.
---

## Decision: Lombok for reducing boilerplate
**Why:**
Java requires a lot of repetitive code: getters, setters, constructors, equals/hashCode. Lombok generates these at compile time via annotations like @Getter, @Builder, @AllArgsConstructor.

**Alternatives:**
Write boilerplate manually, or use Java records (limited — no JPA support for records yet in a straightforward way).

**Tradeoffs:**
Adds a compile-time dependency. Some engineers dislike "magic." But the reduction in noise is significant and it is widely used in production codebases.

---
