# Users API

REST API built with Spring Boot for managing users.

## Features

- CRUD operations for users
- Filtering users
- Sorting users
- Field validations
- Password encryption
- UUID generation
- Timestamp creation
- Swagger API documentation

## Technologies

- Java 21
- Spring Boot
- Maven
- Swagger / OpenAPI

## Run the project

Clone the repository:

git clone https://github.com/your-username/users-api.git

Go to project folder:

cd users-api

Run the application:

./mvnw spring-boot:run

## Swagger Documentation

Open Swagger UI in your browser:

http://localhost:8080/swagger-ui/index.html

## API Endpoints

GET /users  
POST /users  
PATCH /users/{id}  
DELETE /users/{id}

## Example Request

POST /users

```json
{
  "email": "test@test.com",
  "name": "Test User",
  "phone": "+52 5555555555",
  "password": "123456",
  "taxId": "ABCD010101AAA"
}