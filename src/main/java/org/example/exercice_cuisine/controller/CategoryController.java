package org.example.exercice_cuisine.controller;

import jakarta.validation.Valid;
import org.example.exercice_cuisine.model.Category;
import org.example.exercice_cuisine.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;

@Controller
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/categories";
    }

    @GetMapping("/addcategory")
    public String addCategory(Model model) {
        model.addAttribute("category", new Category());
        return "categories/add";
    }

    @PostMapping("/addcategory")
    public String addCategory(@Valid @ModelAttribute("category") Category category, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "categories/add";
        } else {
            categoryService.add(category);
            return "redirect:/categories";
        }
    }

    @GetMapping("/categories/{id}")
    public String categoriesById(@PathVariable UUID id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        return "categories/category";
    }

    @GetMapping("/categories/delete/{id}")
    public String delete(@PathVariable UUID id) {
        categoryService.remove(id);
        return "redirect:/categories";
    }
}
