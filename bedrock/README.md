# Bedrock AI Chat

A Spring Boot application that provides a simple chat interface to AWS Bedrock AI models via Spring AI.

## Tech Stack

- **Java** 17
- **Spring Boot** 4.1.0
- **Spring AI** 2.0.0
- **AWS Bedrock** (via `spring-ai-starter-model-bedrock-converse`)
- **Maven**

## Features

- Simple REST endpoint for chatting with AI models hosted on AWS Bedrock
- Spring AI `ChatClient` integration

## API

### `GET /chat`

Sends a prompt to the configured Bedrock model and returns the response.

**Parameters:**

| Parameter | Type   | Description        |
|-----------|--------|--------------------|
| `prompt`  | String | The user prompt    |

**Example:**

```bash
curl "http://localhost:8080/chat?prompt=Hello%20world"
```

**Response:**

```text
<model response>
```

## Configuration

Configure your AWS credentials and Bedrock model in `application.properties`:

```properties
spring.ai.bedrock.aws.region=us-east-1
spring.ai.bedrock.aws.access-key=${AWS_ACCESS_KEY_ID}
spring.ai.bedrock.aws.secret-key=${AWS_SECRET_ACCESS_KEY}
spring.ai.bedrock.converse.chat.options.model=<your-model-id>
```

## Running

```bash
./mvnw spring-boot:run
```

## Project Structure

```
src/main/java/com/codingshuttle/bedrock/
├── BedrockApplication.java       # Main application
└── controller/
    └── ChatController.java       # /chat endpoint
```