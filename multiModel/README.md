# multiModel

A Spring Boot application that provides a unified REST API interface for interacting with multiple AI models through [Spring AI](https://spring.io/projects/spring-ai).

## Overview

multiModel abstracts away the complexity of different AI provider APIs and exposes simple REST endpoints to chat with:

- **OpenRouter** — using the `stepfun/step-3.5-flash` model
- **AWS Bedrock** — using the `minimax.minimax-m2.1` model

## Tech Stack

- **Java 17**
- **Spring Boot 4.1.0**
- **Spring AI 2.0.0**
- **Maven**

## Project Structure

```
src/main/java/com/codingshuttle/multimodel/
├── MultiModelApplication.java          # Spring Boot entry point
├── controller/
│   └── ChatController.java             # REST endpoints for AI providers
└── multimodelConfig/
    └── ChatClientConfig.java           # ChatClient bean configuration
```

## API Endpoints

| Method | Endpoint                    | Description                    |
|--------|----------------------------|--------------------------------|
| GET    | `/api/v1/openrouter/chat`  | Chat with OpenRouter model     |
| GET    | `/api/v1/aws/chat`         | Chat with AWS Bedrock model    |

### Request Format

Both endpoints accept a `prompt` query parameter:

```bash
# OpenRouter
curl "http://localhost:8080/api/v1/openrouter/chat?prompt=Hello%20world"

# AWS Bedrock
curl "http://localhost:8080/api/v1/aws/chat?prompt=Hello%20world"
```

### Response

Returns the AI model's response as a plain string.

## Configuration

The application uses environment variables for sensitive credentials:

| Property                          | Description                              |
|-----------------------------------|------------------------------------------|
| `API_KEY`                         | OpenRouter API key                       |
| `AWS_REGION`                      | AWS region (e.g., `us-east-1`)           |
| `AWS_ACCESS_KEY`                  | AWS access key                           |
| `AWS_SECRET_KEY`                  | AWS secret key                           |

Additional settings in `src/main/resources/application.properties`:

```properties
spring.application.name=multiModel

# OpenRouter
spring.ai.openai.api-key=${API_KEY}
spring.ai.openai.base-url=https://openrouter.ai/api/v1
spring.ai.openai.chat.model=stepfun/step-3.5-flash

# AWS Bedrock
spring.ai.bedrock.aws.region=${AWS_REGION}
spring.ai.bedrock.aws.access-key=${AWS_ACCESS_KEY}
spring.ai.bedrock.aws.secret-key=${AWS_SECRET_KEY}
spring.ai.bedrock.converse.chat.model=minimax.minimax-m2.1
```

## Building

```bash
./mvnw clean package
```

## Running

```bash
# Set environment variables first, then:
./mvnw spring-boot:run
```

Or with a JAR:

```bash
java -jar target/multiModel-0.0.1-SNAPSHOT.jar
```

## Testing

```bash
./mvnw test
```