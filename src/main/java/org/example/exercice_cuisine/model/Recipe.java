package org.example.exercice_cuisine.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
    private UUID id;
    @NotNull
    @NotBlank(message="Nom obligatoire")
    private String name;
    @Size(min=5, message="Minimum 5 caractères")
    private String ingredients;
    @Size(min=10, max=300, message="Entre 10 et 300 caractères")
    private String instructions;
    private Category category;
}
