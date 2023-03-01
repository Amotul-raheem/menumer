package com.menumer.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecipeRequest {
    private String userId;
    private String recipeId;
    private String recipeName;
    private String description;
    private List<IngredientDetailsRequest> ingredientDetails;
}
