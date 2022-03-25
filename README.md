# ![Atlasmundi](Logo.png) 

## Atlas Mundi


[![](https://img.shields.io/bitbucket/pipelines/beblue/marketplace-service/:branch.svg)](https://bitbucket.org/beblue/marketplace-service/addon/pipelines/home)

> `atlasmundi-service` is responsible for provide features to atlasmundi app


### Endpoints

| Method | URI                                           | Description     | Response Status           |
|--------|-----------------------------------------------|-----------------|---------------------------|
| `POST` | [<u>`/v1/login`](docs/payload/new-login.json) | create a login. | `201`, `202`, `422`, `500`|

### Constraints

| Name           | Description                                |
|----------------|--------------------------------------------|
| `UserNotFound` | UserAndPasswordNotFountException not found |


## Project architecture and organization
### Frameworks and technologies

* Java
* Spring Boot (WebFlux, Data JPA, Actuator)
* Spring Cloud (Sleuth, Stream, Vault, AWS)
* Flyway
* Gradle (Kotlin DSL)
* Java Money API
* Shedlock scheduler

# ![Hexagonal Architecture](hexagonal.jpg)

## How build and run

Environments available:
```bash
spring.profiles.active = staging
spring.profiles.active = production
```

```bash
./gradlew clean build

./gradlew composeUp

java -jar -Dspring.profiles.active=local build/libs/app.jar
```
## Contact information

* Pedro Gomes (pedrogomesup2@gmail.com)
* Mario Massari
