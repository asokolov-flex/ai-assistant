# Research Assistant

A Spring Boot backend service that leverages OpenAI's capabilities to generate images and provide AI-powered assistance. The project integrates Spring AI for seamless interaction with OpenAI's APIs.

## Features

- Image generation using DALL-E 2
- AI chat interactions
- Recipe generation based on ingredients and preferences

## Tech Stack

- Java 17
- Spring Boot 3.5.3
- Spring AI 1.0.0
- OpenAI Integration
- Maven

## Prerequisites

Before running this project, make sure you have:

- JDK 17 or higher
- Maven 3.6+
- OpenAI API key

## Getting Started

1. Clone the repository.
2. Configure OpenAI API key:
   Create `application.properties` in `src/main/resources/` and add:
   spring.ai.openai.api-key=your-api-key-here
3. Build the project:
   mvn clean package
4. Run the application:

The server will start on `http://localhost:8080`

## API Endpoints

### Image Generation
GET /generate-image
Parameters:
- `prompt` (required): Description of the image to generate
- `quality` (optional): Image quality (default: "hd")
- `n` (optional): Number of images to generate (default: 1)
- `width` (optional): Image width in pixels (default: 1024)
- `height` (optional): Image height in pixels (default: 1024)

### AI Chat
GET /ask-ai
Parameters:
- `prompt` (required): Question or prompt for the AI

### Recipe Creation
GET /recipe-creator
Parameters:
- `ingredients` (required): List of available ingredients
- `cuisine` (optional): Preferred cuisine type (default: "any")
- `dietaryRestriction` (optional): Dietary restrictions (default: "any")

## Building for Production

The project is configured to build as a WAR file. To create a production build:
The WAR file will be generated in the `target/` directory.

## Development

### Project Structure
- `GenAIController`: Main REST controller handling API endpoints
- `ImageService`: Service for image generation using DALL-E 2
- `ChatService`: Service for AI chat interactions
- `RecipeService`: Service for recipe generation

### Dependencies

The project uses the following key dependencies:
- `spring-boot-starter-web`: Web application support
- `spring-ai-starter-model-openai`: OpenAI integration
- `spring-boot-starter-test`: Testing support

## Testing

Run the tests using:
```bash
mvn test
```
