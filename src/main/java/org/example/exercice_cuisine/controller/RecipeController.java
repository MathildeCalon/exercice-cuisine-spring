package org.example.exercice_cuisine.controller;

import jakarta.validation.Valid;
import org.example.exercice_cuisine.model.Recipe;
import org.example.exercice_cuisine.service.CategoryService;
import org.example.exercice_cuisine.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class RecipeController {
    private RecipeService recipeService;
    private CategoryService categoryService;

    public RecipeController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @GetMapping("/recipes")
    public String showAllRecipes(Model model) {
        model.addAttribute("recipes", recipeService.getAllRecipes());
        return "recipes/recipes";
    }

    @GetMapping("/recipes/{id}")
    public String showRecipeById(@PathVariable("id") UUID id, Model model) {
        Recipe recipe = recipeService.getRecipeById(id);
        if(recipe == null) {
            return "redirect:/404";
        } else {
            model.addAttribute("recipe", recipe);
            return "recipes/recipe";
        }
    }

    @GetMapping("/recipes/delete/{id}")
    public String deleteRecipe(@PathVariable("id") UUID id) {
        recipeService.removeRecipe(id);
        return "redirect:/recipes";
    }

    @GetMapping("/addrecipe")
    public String addRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("categories", categoryService.findAll());
        return "recipes/add";
    }

    @PostMapping("/addrecipe")
    public String addRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "recipes/add";
        } else {
            recipe.setCategory(categoryService.findById(recipe.getCategory().getId()));
            recipeService.addRecipe(recipe);
            return "redirect:/recipes";
        }
    }

    @GetMapping("/recipes/update/{id}")
    public String updateRecipe(@PathVariable("id") UUID id, Model model) {
        Recipe recipe = recipeService.getRecipeById(id);
        model.addAttribute("recipe", recipe);
        model.addAttribute("categories", categoryService.findAll());
        return "recipes/update";
    }

    @PostMapping("/updaterecipe")
    public String updateRecipe(@Valid @ModelAttribute("recipe") Recipe recipe, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "recipes/update";
        } else {
            recipe.setCategory(categoryService.findById(recipe.getCategory().getId()));
            recipeService.updateRecipe(recipe);
            return "redirect:/recipes";
        }
    }

}
