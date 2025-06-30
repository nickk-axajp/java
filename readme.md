# Java Challenge

This is a simple Spring Boot project for managing employees for the programming test for AXA Japan. It uses an embedded H2 database and exposes a RESTful API with Swagger documentation.

## How to Use

1. **Install dependencies:**  
   `mvn package`

2. **Run the application:**  
   `mvn spring-boot:run`  
   (Or use your IDE to run the main class)

3. **Access the application:**  
   - **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)  
   - **H2 Console:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)  
     - JDBC URL: `jdbc:h2:mem:testdb`

4. **Run tests:**  
   `mvn test`

## Features & Enhancements

- **DTOs:** Added Data Transfer Objects to avoid leaking domain entities through the API.
- **New endpoint:** Added an endpoint to retrieve employees by department.
- **Validation:** Input validation for DTOs to ensure data integrity.
- **Optional in EmployeeService interface:** For more explicit typing instead of using `null` values.
  - This also fixes the issue in the original code where `getEmployeeById` does `.get(id)` without checking the optional value first.
- **RESTful Responses:** Improved API responses to follow RESTful conventions, e.g. using proper status codes .
- **Dependency Injection:** Refactored to use constructor-based DI.
- **Controller Tests:** Added unit tests for controller endpoints.
- **Code Formatting:** Reformatted code using IntelliJ's build in code formatter.

## Comments

- My project would not compile when using Lombok getters and setters, so I opted to use manual getters and setters instead to having a working project.
- If I had more time, I would add the following features:
    - Use mapstruct for DTO mapping
    - Authentication and authorization using Spring Security
    - More comprehensive and global error handling
    - Integration tests for the API
    - Add a formatter, linter, and other static analysis tools (like Checkstyle or SonarQube)
- Disclaimer: I used AI to help translate C# concepts and APIs to Java, but all ideas and implementations are my own.

## My Java Experience

While I have not worked with Java before, I have nearly 8 years of experience in C# and .NET. They are similar, both being object-oriented languages with strong typing and garbage collection.
I would be excited to learn Java in more depth, and I'm sure I could quickly adapt my existing knowledge to the Java ecosystem.

I also have had plenty of professional experience with different programming languages and paradigms, (e.g. functional programming with F#), so I am confident in my ability to learn and adapt to new technologies quickly.
