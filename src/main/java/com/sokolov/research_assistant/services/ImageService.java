package com.sokolov.research_assistant.services;

import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.ai.openai.OpenAiImageModel;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.stereotype.Service;

@Service
public class ImageService {
    OpenAiImageModel imageModel;

    public ImageService(OpenAiImageModel imageModel) {
        this.imageModel = imageModel;
    }
    public ImageResponse generateImage(String prompt) {
        return imageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .model("dall-e-2")
                                .N(3)
                                .height(1024)
                                .width(1024).build()));
    }

    public ImageResponse generateImage(String prompt, String quality, int n, int width, int height) {
        return imageModel.call(
                new ImagePrompt(prompt,
                        OpenAiImageOptions.builder()
                                .quality(quality)
                                .N(n)
                                .height(height)
                                .width(width).build()));
    }
}
