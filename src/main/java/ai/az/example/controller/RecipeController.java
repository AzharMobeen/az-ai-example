package ai.az.example.controller;

import ai.az.example.dto.RecipeRequest;
import ai.az.example.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gen-ai-recipe")
public class RecipeController {

    Logger log = LoggerFactory.getLogger(RecipeController.class);
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public String getAIRecipe(@RequestParam String ingredients, @RequestParam(defaultValue = "any") String cuisine,
                            @RequestParam(defaultValue = "") String dietaryRestrictions, @RequestParam(defaultValue = "English") String lang) {
        log.info("Recipe controller is running");
        RecipeRequest recipeRequest = new RecipeRequest(ingredients, cuisine, dietaryRestrictions, lang);
        return recipeService.generateAIRecipe(recipeRequest);
    }
}
