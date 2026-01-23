# PetStore — E-commerce Pet Shop (Spring Boot + Thymeleaf)

This repository contains a course/final-project style e-commerce pet store built with Spring Boot, Spring Data JPA and Thymeleaf. The purpose of the repo is to be a clear, presentable demo of a typical Java web application — suitable for browsing on GitHub.

This README is written to help a reader understand the project structure, how to run it for local demo purposes, and how to prepare the repo for sharing on GitHub.

## Highlights

- Java: 11
- Build: Maven (project includes `mvnw` wrapper, but note: some wrapper files may be missing in this copy — using the system `mvn` is fine)
- Default DB: MySQL (see `src/main/resources/application.yml`)
- Local demo: a `local` Spring profile is provided which uses an in-memory H2 database (`src/main/resources/application-local.yml`)

## What I changed (so the repo is easier to run / review)

- Added `com.h2database:h2` runtime dependency to `pom.xml` so the project can run against an in-memory database for quick demos.
- Added `application-local.yml` and configured it to activate on the `local` profile using `spring.config.activate.on-profile=local`.
- Polished this README and added a `.gitignore` to keep the repo tidy for GitHub.

## Important: `mvnw` (Maven Wrapper) — what it is and what to do

`mvnw` is the Maven Wrapper script. It allows someone to build the project with a stable Maven version without installing Maven system-wide. A complete wrapper includes:

- `mvnw` (Unix shell script), `mvnw.cmd` (Windows), and the `.mvn/wrapper` directory containing `maven-wrapper.jar` and `maven-wrapper.properties`.

If the wrapper files are missing (for example, `.mvn/wrapper/maven-wrapper.jar`), the wrapper will fail. In that case either:

- Install Maven on your machine and run `mvn ...` commands, or
- Restore the missing wrapper files (often simplest: run `mvn -N io.takari:maven:wrapper` on a machine that has Maven).

This repository includes `mvnw` but some wrapper artifacts were incomplete in the copy I received — using the system `mvn` is the easiest approach.

## Asset policy (PNG / images)

You mentioned PNG files should not be uploaded. Large binary assets bloat repository size and make cloning slow. Recommended options:

1. Remove PNGs from the repository and host them externally (CDN, S3, or object storage) and reference the URLs from templates.
2. Use Git LFS for large binary assets (if you want them in the repo but not in git history directly).
3. Keep only small icons/SVGs; avoid committing full-resolution photos.

I updated `.gitignore` to exclude common static image patterns so they won't be accidentally committed. If you want to keep some small images, move them to a separate folder that is not ignored, or remove the ignore rule and add a selective list.

## Run the project locally (quick demo using H2)

Prerequisites (only if you plan to run):

- JDK 11 installed (OpenJDK or Oracle JDK)
- Maven installed (system `mvn`) — only required if the wrapper is incomplete

Steps to run with the in-memory demo profile:

```bash
cd /path/to/pet_store/pet_store
# If wrapper is available and complete, you can use ./mvnw; otherwise use system mvn
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

After startup:

- Application: http://localhost:8080
- H2 console: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:petdb`
  - Username: `sa`  (password: leave empty)

To create an executable JAR for distribution:

```bash
mvn -DskipTests package
java -jar target/PetStore-0.0.1-SNAPSHOT.jar --spring.profiles.active=local
```

## Run with MySQL (production-like)

1. Create the `pet_store` database and a user in MySQL. Example SQL:

```sql
CREATE DATABASE pet_store CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER 'petuser'@'localhost' IDENTIFIED BY 'yourpassword';
GRANT ALL PRIVILEGES ON pet_store.* TO 'petuser'@'localhost';
FLUSH PRIVILEGES;
```

2. Update `src/main/resources/application.yml` `spring.datasource.url`, `username`, and `password` (or set the equivalent environment variables `SPRING_DATASOURCE_*`).

3. Run without the `local` profile:

```bash
mvn spring-boot:run
```

## Project layout (short)

- `src/main/java/.../petstore` — controllers, services, DAOs, entities
- `src/main/resources/templates` — Thymeleaf templates
- `src/main/resources/static` — CSS/JS/images used by templates
- `pom.xml` — Maven build file

## Recommended GitHub presentation

- Keep the repository lightweight: do not commit large images. Use `.gitignore` (already added).
- Add a short screenshot or a single small, optimized image (PNG or SVG) if you want a visual in the README — but avoid high-resolution photos.
- Add a brief `CONTRIBUTING.md` and license if you plan to share publicly.