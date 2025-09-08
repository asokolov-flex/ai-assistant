package com.sokolov.research_assistant;

import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import java.util.List;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public ChatModel testChatModel() {
        return prompt -> {
            AssistantMessage message = new AssistantMessage("Test response");
            return new ChatResponse(List.of(new Generation(message)));
        };
    }

}
