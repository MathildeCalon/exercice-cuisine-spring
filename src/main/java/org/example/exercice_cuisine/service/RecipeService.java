package org.example.exercice_cuisine.service;

import org.example.exercice_cuisine.model.Category;
import org.example.exercice_cuisine.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecipeService {
    private final Map<UUID, Recipe> recipes;

    public RecipeService() {
        recipes = new HashMap<>();
    }

    public Recipe getRecipeById(UUID id) {
        return recipes.get(id);
    }

    public List<Recipe> getAllRecipes() {
        return new ArrayList<>(recipes.values());
    }

    public void addRecipe(Recipe recipe) {
        recipe.setId(UUID.randomUUID());
        recipes.put(recipe.getId(), recipe);
    }

    public void removeRecipe(UUID id) {
        recipes.remove(id);
    }

    public void updateRecipe(Recipe recipe) {
        Recipe oldRecipe = getRecipeById(recipe.getId());
        if (oldRecipe != null) {
            oldRecipe.setName(recipe.getName());
            oldRecipe.setIngredients(recipe.getIngredients());
            oldRecipe.setInstructions(recipe.getInstructions());
            oldRecipe.setCategory(recipe.getCategory());
        }
    }
}
