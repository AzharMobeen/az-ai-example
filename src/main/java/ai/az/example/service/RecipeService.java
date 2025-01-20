package ai.az.example.service;


import ai.az.example.dto.RecipeRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecipeService {

    Logger log = LoggerFactory.getLogger(RecipeService.class);

    private final ChatModel chatModel;


    public RecipeService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String generateAIRecipe(RecipeRequest recipeRequest) {
        log.info("Recipe service is running for [{}]", recipeRequest);
        var template = """
                I want to create a recipe with the following ingredients: {ingredients}.
                I want the cuisine to be {cuisine}.
                Please consider the following dietary restrictions: {dietaryRestrictions}.
                please provide me with a detailed recipe including title, list of ingredients, and cooking instructions In {lang} language.
                """;
        Map<String, Object> params = Map.of("ingredients", recipeRequest.ingredients(), "cuisine", recipeRequest.cuisine(),
                "dietaryRestrictions", recipeRequest.dietaryRestrictions(), "lang", recipeRequest.lang());
        PromptTemplate promptTemplate = new PromptTemplate(template, params);
        return chatModel.call(promptTemplate.createMessage());
    }
}