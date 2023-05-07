package com.menumer.controller;

import com.menumer.model.Recipe;
import com.menumer.model.User;
import com.menumer.payload.request.RecipeRequest;
import com.menumer.repository.RecipeRepository;
import com.menumer.service.RecipeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/recipe")
@Slf4j
@AllArgsConstructor
public class RecipeController {
    private RecipeService recipeService;

    @GetMapping(value = "/user/{userId}")
    public Set<Recipe> getUserRecipes(@PathVariable String userId) {
        return recipeService.getUserRecipes(userId);
    }

    @GetMapping(value = "recipe/{recipeId}")
    public ResponseEntity<?> getRecipe(@PathVariable String recipeId) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        return new ResponseEntity<>(recipe, HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createRecipe(@RequestBody final RecipeRequest recipeRequest) {
        Recipe createdRecipe = recipeService.createRecipe(recipeRequest);
        if (ObjectUtils.isEmpty(createdRecipe)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(createdRecipe, HttpStatus.OK);
    }

    @PostMapping(value = "/user/save/{recipeId}")
    public ResponseEntity<?> saveRecipe(@PathVariable String recipeId,
                                          @RequestHeader(name = "USER_ID",
                                                  required = true) String userId) {
        User user = recipeService.saveRecipe(recipeId,userId);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @PostMapping(value = "/update")
    public ResponseEntity<?> updateRecipe(@RequestBody final RecipeRequest recipeRequest) {
        Recipe updatedRecipe = recipeService.updateRecipe(recipeRequest);
        if (ObjectUtils.isEmpty(updatedRecipe)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{recipeId}")
    public ResponseEntity<?> deleteRecipe(@PathVariable String recipeId,
                                          @RequestHeader(name = "USER_ID",
                                                  required = true ) String userId){
        recipeService.deleteRecipe(recipeId, userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
