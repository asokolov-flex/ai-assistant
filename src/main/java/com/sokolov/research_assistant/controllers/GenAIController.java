package com.sokolov.research_assistant.controllers;

import com.sokolov.research_assistant.dto.ChatResponseDto;
import com.sokolov.research_assistant.services.ChatService;
import com.sokolov.research_assistant.services.ImageService;
import com.sokolov.research_assistant.services.RecipeService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenAIController {

    private final ChatService chatService;

    private final ImageService imageService;

    private final RecipeService recipeService;

    public GenAIController(ChatService chatService, ImageService imageService, RecipeService recipeService) {
        this.chatService = chatService;
        this.imageService = imageService;
        this.recipeService = recipeService;
    }

    @GetMapping("ask-ai")
    public String getResponse(@RequestParam String prompt){
        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-gpt5")
    public ResponseEntity<ChatResponseDto> getResponseGpt5(@RequestParam String prompt){
        String response = chatService.getResponseOptions(prompt);
        return ResponseEntity.ok(new ChatResponseDto(response));
    }

//    @GetMapping("generate-image")
//    public void generateImage(HttpServletResponse response, @RequestParam String prompt){
//        ImageResponse imageResponse = imageService.generateImage(prompt);
//
//        var url = imageResponse.getResult().getOutput().getUrl();
//
//        try {
//            response.sendRedirect(url);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @GetMapping("generate-image")
    public List<String> generateImage(HttpServletResponse response,
                                      @RequestParam String prompt,
                                      @RequestParam(value = "quality", defaultValue = "hd") String quality,
                                      @RequestParam(value = "n", defaultValue = "1") int n,
                                      @RequestParam(value = "width", defaultValue = "1024") int width,
                                      @RequestParam(value = "height", defaultValue = "1024") int height){
        ImageResponse imageResponse = imageService.generateImage(prompt, quality, n, width, height);

        // Streams to get urls from ImageResponse
        List<String> imageUrls = imageResponse.getResults().stream().map(result -> result.getOutput().getUrl()).toList();

        return imageUrls;
    }

    @GetMapping("recipe-creator")
    public String recipeCreator(HttpServletResponse response,
                                      @RequestParam String ingredients,
                                      @RequestParam(defaultValue = "any") String cuisine,
                                      @RequestParam(defaultValue = "any") String dietaryRestriction){
        return recipeService.createRecipe(ingredients, cuisine, dietaryRestriction);
    }
}
