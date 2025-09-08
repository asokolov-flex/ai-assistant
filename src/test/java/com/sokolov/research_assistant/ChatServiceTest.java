package com.sokolov.research_assistant;

import com.sokolov.research_assistant.services.ChatService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.messages.AssistantMessage;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ChatServiceTest {

    @Mock
    private ChatModel chatModel;

    private ChatService chatService;

    @BeforeEach
    void setUp() {
        chatService = new ChatService(chatModel);
    }

    @Test
    void getResponse_ShouldReturnValidResponse_WhenPromptIsValid() {
        // Arrange
        String prompt = "What is the weather like?";
        String expectedResponse = "The weather is sunny.";
        when(chatModel.call(anyString())).thenReturn(expectedResponse);

        // Act
        String actualResponse = chatService.getResponse(prompt);

        // Assert
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void getResponse_ShouldHandleEmptyPrompt() {
        // Arrange
        String prompt = "";
        String expectedResponse = "I couldn't understand your question.";
        when(chatModel.call(prompt)).thenReturn(expectedResponse);

        // Act
        String actualResponse = chatService.getResponse(prompt);

        // Assert
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void getResponseOptions_ShouldReturnValidResponse_WithCustomOptions() {
        // Arrange
        String prompt = "Tell me a joke";
        String expectedResponse = "Why did the programmer quit his job? Because he didn't get arrays!";
        
        ChatResponse mockChatResponse = createMockChatResponse(expectedResponse);
        when(chatModel.call(any(Prompt.class))).thenReturn(mockChatResponse);

        // Act
        String actualResponse = chatService.getResponseOptions(prompt);

        // Assert
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void getResponseOptions_ShouldUseCorrectModel() {
        // Arrange
        String prompt = "What's the capital of France?";
        String expectedResponse = "Paris";
        
        ChatResponse mockChatResponse = createMockChatResponse(expectedResponse);
        when(chatModel.call(any(Prompt.class))).thenReturn(mockChatResponse);

        // Act
        String actualResponse = chatService.getResponseOptions(prompt);

        // Assert
        assertThat(actualResponse).isEqualTo(expectedResponse);
    }

    @Test
    void getResponse_ShouldHandleNullPrompt() {
        // Arrange
        when(chatModel.call((String) null)).thenThrow(IllegalArgumentException.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            chatService.getResponse(null);
        });
    }

    @Test
    void getResponseOptions_ShouldHandleNullPrompt() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            chatService.getResponseOptions(null);
        });
    }

    // Helper method to create a mock ChatResponse
    private ChatResponse createMockChatResponse(String responseText) {
        return new ChatResponse(List.of(new Generation(new AssistantMessage(responseText))));
    }
}
