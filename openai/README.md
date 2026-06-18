# OpenAI Chat

A Spring Boot application that provides a simple chat interface to OpenAI models via Spring AI.

## Tech Stack

- **Java** 17
- **Spring Boot** 4.1.0
- **Spring AI** 2.0.0
- **OpenAI** (via `spring-ai-starter-model-openai`)
- **Maven**

## Features

- Simple REST endpoint for chatting with OpenAI models
- Spring AI `ChatClient` integration

## API

### `GET /chat`

Sends a prompt to the configured OpenAI model and returns the response.

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

Configure your OpenAI API key in `application.properties`:

```properties
spring.ai.openai.api-key=${OPENAI_API_KEY}
spring.ai.openai.chat.options.model=gpt-4o
```

## Running

```bash
./mvnw spring-boot:run
```

## Project Structure

```
src/main/java/com/codingshuttle/openai/
├── OpenaiApplication.java       # Main application
└── controller/
    └── ChatController.java      # /chat endpoint
```