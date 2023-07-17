# Users API

This is a sample project showcasing a RESTful API for managing user records.

## Prerequisites

- Java 11 or higher
- Maven
- Docker

## Getting Started

1. Clone the repository:

```shell
git clone https://github.com/phadzizi/users-api.git
```
2. Build the project

```shell
cd users-api
mvn clean install
```

3. Build the Docker Image

```shell
docker build -t users-api-app .
```

4. Run the application in a docker container

```shell
docker-compose up
```

The application will start on http://localhost:8088


## API Endpoints
- GET /users - Retrieve a list of all users.
- GET /users/{id} - Retrieve a specific user by ID.
- POST /users - Create a new user.
- PUT /users/{id} - Update an existing user.
- DELETE /users/{id} - Delete a user.

## Technologies Used
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

## Configuration
The application's configuration can be modified in the application.properties file, located in the src/main/resources directory. You can update the database connection settings, server port, and other properties as needed.

## Testing

```shell
mvn test
```

