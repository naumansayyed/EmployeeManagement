# Employee Management System

A RESTful Employee Management application built using Java and Spring Boot.

## Technologies Used
- Java 21
- Spring Boot
- Spring Data JPA
- Oracle Database
- Maven
- REST APIs
- JUnit 5 and Mockito
- Postman
- Git and GitHub

## Features
- Create a new employee
- Retrieve all employees
- Retrieve employee by ID
- Update employee details
- Delete an employee
- Search employees by name
- Input validation and exception handling
- Pagination
- Logging
- Development and production profiles
- Unit testing

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | `/employees` | Get all employees |
| POST | `/employees` | Create an employee |
| GET | `/employees/{id}` | Get employee by ID |
| PUT | `/employees/{id}` | Update an employee |
| DELETE | `/employees/{id}` | Delete an employee |
| GET | `/employees/search?name=John` | Search by name |

## How to Run
1. Clone this repository.
2. Open the project in Eclipse or Spring Tools.
3. Configure your Oracle database connection locally.
4. Run `EmployeeManagementApplication.java`.
5. Test the APIs using Postman.

## Author
Nauman
