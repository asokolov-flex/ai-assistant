package com.sokolov.research_assistant.dto;

public class ChatResponseDto {
    private String answer;

    public ChatResponseDto(String answer) {
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
