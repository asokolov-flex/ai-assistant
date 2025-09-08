package com.sokolov.research_assistant;

import com.sokolov.research_assistant.services.ChatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ChatServiceIntegrationTest {

    @Autowired
    private ChatService chatService;

    @Test
    void getResponse_ShouldReturnNonEmptyResponse() {
        // Arrange
        String prompt = "Hello, how are you?";

        // Act
        String response = chatService.getResponse(prompt);

        // Assert
        assertThat(response).isNotNull().isNotEmpty();
    }

    @Test
    void getResponseOptions_ShouldReturnNonEmptyResponse() {
        // Arrange
        String prompt = "Tell me a joke";

        // Act
        String response = chatService.getResponseOptions(prompt);

        // Assert
        assertThat(response).isNotNull().isNotEmpty();
    }
}
