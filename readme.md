# AZ AI Example

This is a Spring Boot application that provides a RESTful API for interacting with an AI chat service. The application uses the OpenAI API to generate responses based on user prompts.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6.3 or higher
- An OpenAI API key

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/AzharMobeen/az-ai-example.git
   cd az-ai-example
   ```
2. Branch out to the branch you want to work on:
   ```sh
   git checkout feature/ai-chat
   ```

3. Set the OpenAI API key as an environment variable:
   ```sh
   export AZ_AI_CHATGPT_KEY=your_api_key_here
   ```

4. Build the application:
   ```sh
   mvn clean install
   ```

5. Run the application:
   ```sh
   mvn spring-boot:run
   ```

### Configuration

The application reads the OpenAI API key from an environment variable. Ensure that the `AZ_AI_CHATGPT_KEY` environment variable is set before running the application.

### Endpoints

The application provides the following endpoints:

#### GET /chat/ask-ai

This endpoint generates a response from the AI based on the provided prompt.

- **URL**: `/chat/ask-ai`
- **Method**: `GET`
- **Query Parameter**: `prompt` (required) - The prompt to send to the AI.
- **Response**: A string containing the AI's response.

**Example Request**:
```sh
curl -X GET "http://localhost:8080/chat/ask-ai?prompt=What is the meaning of life?"
```

**Example Response**:
```json
"The meaning of life is a philosophical question that has been debated for centuries. Some believe it is to seek happiness and fulfillment, while others think it is to fulfill a higher purpose or destiny."
```

#### GET /chat/ask-ai-options

This endpoint generates a response from the AI with additional options.

- **URL**: `/chat/ask-ai-options`
- **Method**: `GET`
- **Query Parameter**: `prompt` (required) - The prompt to send to the AI.
- **Response**: A string containing the AI's response with options.

**Example Request**:
```sh
curl -G "http://localhost:8080/chat/ask-ai-options" --data-urlencode "prompt=Tell me a joke."
```

**Example Response**:
```json
"Why don't scientists trust atoms? Because they make up everything!"
```

### Reference Documentation

For further reference, please consider the following sections:

- [Spring OpenAI](https://docs.spring.io/spring-ai/reference/api/chat/openai-chat.html)
- [OpenAI Documentation](https://platform.openai.com/docs/overview)
- [OpenAI Platform API parameters](https://platform.openai.com/docs/api-reference/chat/create)
