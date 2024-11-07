# Shopping Site Backend

## Overview
This project is the backend service for a shopping site. It is built using Java and Kotlin, leveraging the Spring Boot framework. The project uses Gradle for dependency management and build automation.

## E-commerce Description
The backend service supports a comprehensive e-commerce platform, providing essential functionalities such as product management, order processing, payment integration, and customer management. It ensures a seamless shopping experience with robust security measures and efficient data handling.

## Features
- User management (CRUD operations for shoppers)
- Authentication and authorization using Spring Security and OAuth2
- Data persistence with Spring Data JPA and PostgreSQL
- API documentation with Swagger
- Code formatting with Spotless

## Technologies
- **Java 17**
- **Kotlin**
- **Spring Boot 3.3.4**
- **Spring Data JPA**
- **Spring Security**
- **OAuth2**
- **PostgreSQL**
- **Swagger**
- **Gradle**

## Getting Started

### Prerequisites
- JDK 17 or higher
- Gradle
- PostgreSQL

### Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/shopping-site-backend.git
    cd shopping-site-backend
    ```

2. Configure the database:
    - Create a PostgreSQL database and update the `application.properties` file with your database credentials.

3. Build the project:
    ```sh
    ./gradlew build
    ```

4. Run the application:
    ```sh
    ./gradlew bootRun
    ```

### Running Tests
To run the tests, use the following command:
```sh
./gradlew test