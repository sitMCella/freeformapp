# OpenApi 3 FreeForm object conversion

This project consists of a web application with a converter for a custom OpenApi 3 Free Form object.
The custom object is a complex object with one inner object.
This implementation is an extension of the OpenOpi 3 specification.

The official OpenAPI 3 documentation is: [OpenAPI 3.0.0 documentation](https://github.com/OAI/OpenAPI-Specification/blob/master/versions/3.0.0.md).

The OpenApi 3 free-form object definition in OpenApi 3 document "documents/user.yaml" has been created by the project:
https://github.com/sitMCella/openapi-3-object-converter.

## Development

### Setup

Install OpenJDK 11.

### Build project

```
gradlew clean build
```

### Create Docker images

```
gradlew jibDockerBuild
```

### Run application

```
docker-compose up
```

### Example REST API Request

```
http://localhost:8080/api/user?firstName=Henry&lastName=Lee&age=28&weight=78&address.street=Tincidunt%20Ave&address.houseNumber=50&address.city=Sedalia&address.postCode=53700
```

## REST APIs documentation

The folder "documents" contains the OpenAPI REST APIs documentation.
The documentation is compliant with Swagger UI and Postman.

Run the following command to validate the OpenAPI documentation file:
 
```
gradlew validateOpenApi
```
