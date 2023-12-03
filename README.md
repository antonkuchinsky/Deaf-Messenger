# Deaf-Messenger
### The deaf-messenger is a project for one-on-one correspondence
The project utilizes various technologies including:
> Java 17, Spring Boot, Gradle, PostgreSQL, Spring Data JPA, Zipkin, Eureka Server, JWT, REST, Flyway, Docker, Junit, Kafka, WebSockets, Grafana.

#Application architecture
>![Image alt](https://github.com/antonkuchinsky/Deaf-Messenger/raw/main/image.png)
# User Service
The user service implements the logic for adding a user profile after user registration using a Kafka listener, updating user profiles, and adding user contacts with storage and pagination support.

# Discovery Service
This service is implemented for service discovery.

# Auth Service
The auth service handles authentication and authorization.
It utilizes JWT tokens to validate their authenticity and can issue new tokens.
User registration includes password encryption and the creation of user profiles through communication with the user service.

# Gateway Service
The gateway service is responsible for distributing requests to the respective services.
It filters requests and prevents unauthorized users from accessing the services.

#Message Service
The message service implements the logic for sending messages to users using WebSockets, creating chats between users, and retrieving a list of messages in a chat with pagination support.
