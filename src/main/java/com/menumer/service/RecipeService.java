package com.menumer.service;

import com.menumer.model.IngredientDetails;
import com.menumer.model.Recipe;
import com.menumer.model.User;
import com.menumer.model.UserProfile;
import com.menumer.model.enums.Action;
import com.menumer.payload.request.RecipeRequest;
import com.menumer.repository.RecipeRepository;
import com.menumer.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class RecipeService {
    private UserRepository userRepository;
    private RecipeRepository recipeRepository;

    private IngredientService ingredientService;

    public Set<Recipe> getUserRecipes(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            log.info("User with id {} does not exist", userId);
            return null;
        }
        User user = userOptional.get();
        return user.getCreatedRecipes();
    }

    public Recipe createRecipe(RecipeRequest recipeRequest) {
        String userId = recipeRequest.getUserId();
        String recipeName = recipeRequest.getRecipeName();
        String description = recipeRequest.getDescription();
        List<IngredientDetails> ingredientDetails = ingredientService.saveIngredients(recipeRequest.getIngredientDetails());
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isEmpty()) {
            log.info("User with id {} does not exist", userId);
            return null;
        }
        User user = userOptional.get();

        Recipe recipe = Recipe.builder()
                .recipeName(recipeName)
                .description(description)
                .user(user)
                .ingredientDetails(ingredientDetails)
                .build();
        recipeRepository.save(recipe);
        Set<Recipe> createdRecipes = user.getCreatedRecipes();
        createdRecipes.add(recipe);
        user.setCreatedRecipes(createdRecipes);
        userRepository.save(user);
        return recipe;
    }

    public void createUser() {
        String firstName = "Amat";
        String lastName = "Olajide";
        String email = "test@example.com";
        String username = "Amat";
        String password = "testing";

        log.info("Creating User...");
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .username(username)
                .password(password)
                .build();

        userRepository.save(user);

    }

    public User saveRecipe(String recipeId, String userId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (recipeOptional.isEmpty()) {
            log.info("Recipe with id {} does not exist", recipeId);
            return null;
        }
        if (userOptional.isEmpty()) {
            log.info("User with id {} does not exist", userId);
            return null;
        }

        Recipe recipe = recipeOptional.get();
        User user = userOptional.get();

        UserProfile userProfile = user.getUserProfile();
        if (ObjectUtils.isEmpty(userProfile)) {
            userProfile = new UserProfile();
        }
        userProfile.addSavedRecipe(recipe);
        user.setUserProfile(userProfile);
        userRepository.save(user);
        return user;
    }

    public Recipe updateRecipe(RecipeRequest recipeRequest) {
        String recipeId = recipeRequest.getRecipeId();
        String recipeName = recipeRequest.getRecipeName();
        String description = recipeRequest.getDescription();
        List<IngredientDetails> ingredientDetails = ingredientService.saveIngredients(recipeRequest.getIngredientDetails());

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (recipeOptional.isEmpty()) {
            log.info("Recipe with id {} does not exist", recipeId);
            return null;
        }

        Recipe recipe = recipeOptional.get();
        recipe.setRecipeName(recipeName);
        recipe.setDescription(description);
        recipe.setIngredientDetails(ingredientDetails);

        recipeRepository.save(recipe);
        return recipe;

    }

    public void deleteRecipe(String recipeId, String userId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);
        Optional<User> userOptional = userRepository.findById(userId);

        if (recipeOptional.isEmpty()) {
            log.info("Recipe with id {} does not exist", recipeId);
            return;
        }
        if (userOptional.isEmpty()) {
            log.info("User with id {} does not exist", userId);
            return;
        }
        Recipe recipe = recipeOptional.get();
        User user = userOptional.get();
        if (!StringUtils.equals(recipe.getUser().getId(), (userId))) {
            log.error("User with id {} is trying to delete a recipe {} that wasn't created by him", userId, recipeId);
            return;
        }
        recipeRepository.delete(recipe);
        Set<Recipe> updatedRecipes = user.getCreatedRecipes();
        updatedRecipes.remove(recipe);
        user.setCreatedRecipes(updatedRecipes);
        userRepository.save(user);
    }
}
