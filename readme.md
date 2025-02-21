# AZ AI Example

This is a Spring Boot application that provides a RESTful API for interacting with an AI chat service, generating AI images, and creating AI recipes. The application uses the OpenAI API to generate responses based on user prompts.

## Table of Contents

- [Getting Started](#getting-started)
  - [Prerequisites](#prerequisites)
  - [Installation](#installation)
  - [Configuration](#configuration)
- [Functionality](#functionality)
  - [Chat Endpoints](#chat-endpoints)
    - [GET /chat/ask-ai](#get-chatask-ai)
    - [GET /chat/ask-ai-options](#get-chatask-ai-options)
  - [Image Generation Endpoints](#image-generation-endpoints)
    - [GET /image/gen-ai-image](#get-imagegen-ai-image)
    - [GET /image/gen-ai-image-url](#get-imagegen-ai-image-url)
  - [Recipe Generation Endpoints](#recipe-generation-endpoints)
    - [GET /gen-ai-recipe](#get-gen-ai-recipe)
- [Reference Documentation](#reference-documentation)
- [Guides](#guides)
- [License](#license)

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

## Functionality

### Chat Endpoints

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

### Image Generation Endpoints

#### GET /image/gen-ai-image

This endpoint generates an AI image based on the provided prompt and redirects to the image URL.

- **URL**: `/image/gen-ai-image`
- **Method**: `GET`
- **Query Parameters**:
  - `prompt` (required) - The prompt to send to the AI.
  - `quality` (optional, default: "hd") - The quality of the image.
  - `numberOfImages` (optional, default: 1) - The number of images to generate.
  - `width` (optional, default: 1024) - The width of the image.
  - `height` (optional, default: 1024) - The height of the image.
  - `responseType` (optional, default: "URL") - The response type (URL or B64_JSON).
- **Response**: Redirects to the generated image URL.

**Example Request**:
```sh
curl -X GET "http://localhost:8080/image/gen-ai-image?prompt=A beautiful sunset&quality=hd&numberOfImages=1&width=1024&height=1024&responseType=URL"
```
**Example Response**:
```
Run above request in Browser it will redirect to the image URL
```

#### GET /image/gen-ai-image-url

This endpoint generates an AI image based on the provided prompt and returns the image URL.

- **URL**: `/image/gen-ai-image-url`
- **Method**: `GET`
- **Query Parameters**:
  - `prompt` (required) - The prompt to send to the AI.
  - `quality` (optional, default: "hd") - The quality of the image.
  - `numberOfImages` (optional, default: 1) - The number of images to generate.
  - `width` (optional, default: 1024) - The width of the image.
  - `height` (optional, default: 1024) - The height of the image.
  - `responseType` (optional, default: "URL") - The response type (URL or B64_JSON).
- **Response**: A string containing the generated image URL.

**Example Request**:
```sh
curl -X GET "http://localhost:8080/image/gen-ai-image-url?prompt=A beautiful sunset&quality=hd&numberOfImages=1&width=1024&height=1024&responseType=URL"
```

**Example Response**:
```json
"https://oaidalleapiprodscus.blob.core.windows.net/private/org-8VWSsI3T6M6UpkICnaRoBhI0/user-cz0WrqIdtbPmTritXw9d8HOU/img-qHIW4CviatPeWk7IYwdFTV4J.png?st=2025-01-19T11%3A19%3A04Z&se=2025-01-19T13%3A19%3A04Z&sp=r&sv=2024-08-04&sr=b&rscd=inline&rsct=image/png&skoid=d505667d-d6c1-4a0a-bac7-5c84a87759f8&sktid=a48cca56-e6da-484e-a814-9c849652bcb3&skt=2025-01-19T00%3A52%3A48Z&ske=2025-01-20T00%3A52%3A48Z&sks=b&skv=2024-08-04&sig=bRhRpG0IlTA0AX3x/%2BVvKwiVzbrT36hQRJAsmzJQK9E%3D"
```

### Recipe Generation Endpoints

#### GET /gen-ai-recipe

This endpoint generates an AI recipe based on the provided ingredients, cuisine, dietary restrictions, and language.

- **URL**: `/gen-ai-recipe`
- **Method**: `GET`
- **Query Parameters**:
  - `ingredients` (required) - The ingredients for the recipe.
  - `cuisine` (optional, default: "any") - The type of cuisine.
  - `dietaryRestrictions` (optional, default: "") - Any dietary restrictions.
  - `lang` (optional, default: "English") - The language for the recipe.
- **Response**: A string containing the generated recipe.

**Example Request**:
```sh
curl -X GET "http://localhost:8080/gen-ai-recipe?ingredients=chicken,tomato&cuisine=Italian&dietaryRestrictions=gluten-free&lang=English"
```

**Example Response**:
```json
{
  "title": "Gluten-Free Italian Chicken",
  "ingredients": ["chicken", "tomato", "olive oil", "garlic", "basil"],
  "instructions": "1. Heat olive oil in a pan. 2. Add garlic and sauté until golden. 3. Add chicken and cook until browned. 4. Add tomatoes and basil, and simmer for 20 minutes. 5. Serve hot."
}
```

## Reference Documentation

For further reference, please consider the following sections:

- [Spring OpenAI](https://docs.spring.io/spring-ai/reference/api/chat/openai-chat.html)
- [OpenAI Documentation](https://platform.openai.com/docs/overview)
- [OpenAI Platform API parameters](https://platform.openai.com/docs/api-reference/chat/create)
- [OpenAI image generation API](https://platform.openai.com/docs/api-reference/images)
- [OpenAI Audio Transcription API](https://docs.spring.io/spring-ai/reference/api/audio/transcriptions/openai-transcriptions.html)
