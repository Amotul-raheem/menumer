package com.menumer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile {
    private Set<Recipe> likedRecipes;
    private Set<Recipe> savedRecipes;
    private Set<String> followers;
    private Set<String> following;

    public void addSavedRecipe(Recipe recipe) {
        if(CollectionUtils.isEmpty(savedRecipes)){
            savedRecipes = new HashSet<>();
        }
        savedRecipes.add(recipe);
    }
}
