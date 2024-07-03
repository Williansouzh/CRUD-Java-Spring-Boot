# CRUD API REST JAVA SPRING BOOT

ParkingSpot API Rest

## Installation

Ensure you have Java JDK and Maven installed. Clone the repository and run the following command to build the project:

```bash
mvn clean install
```

## Configuration

Before starting the server, configure the necessary environment variables, such as database connection, API keys, etc. Rename the `application.properties.example` file to `application.properties` and fill in the variables as needed.

## Running the Server

To start the server, run the following command:

```bash
mvn spring-boot:run
```

The server will run on the default port `8080`, unless otherwise specified in the environment variables.

## Endpoints

### GET /parking-spot

Retrieve all parking spots.

### POST /parking-spot

Create a new parking spot.

### PATCH /parking-spot/{id}

Update an existing parking spot by ID.

### DELETE /parking-spot/{id}

Delete a parking spot by ID.

## Project Structure

- `src/main/java/`
  - `controllers/` - Data manipulation logic controllers.
  - `models/` - Database models.
  - `repositories/` - Data access layer.
  - `services/` - Business logic of the application.
  - `dtos/` - Data transfer objects.
  - `config/` - Spring Boot configurations.

## Technologies Used

- Java
- Spring Boot
- Maven
- Swagger
- JPA/Hibernate
- PostgreSQL

## Contributing

1. Fork the project
2. Create your branch (`git checkout -b feature/MyFeature`)
3. Commit your changes (`git commit -am 'Adding MyFeature'`)
4. Push to the branch (`git push origin feature/MyFeature`)
5. Create a new Pull Request
