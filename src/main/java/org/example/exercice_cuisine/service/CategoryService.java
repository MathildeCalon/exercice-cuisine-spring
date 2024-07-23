package org.example.exercice_cuisine.service;

import org.example.exercice_cuisine.model.Category;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService {
    private Map<UUID, Category> categories;

    public CategoryService() {
        this.categories = new HashMap<>();
        Category dessert = Category.builder()
                .id(UUID.randomUUID())
                .name("Dessert")
                .description("Sweet")
                .build();
        categories.put(dessert.getId(), dessert);
    }

    public Category getCategoryByName(String name) {
        return this.categories.get(name);
    }

    public Category findById(UUID id) {
        return categories.get(id);
    }

    public List<Category> findAll() {
        return new ArrayList<>(categories.values());
    }

    public void add(Category category) {
        category.setId(UUID.randomUUID());
        categories.put(category.getId(), category);
    }

    public void remove(UUID id) {
        categories.remove(id);
    }
}
