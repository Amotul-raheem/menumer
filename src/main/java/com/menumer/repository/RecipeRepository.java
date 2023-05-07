package com.menumer.repository;

import com.menumer.model.Recipe;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends MongoRepository<Recipe, String> {
    Optional<List<Recipe>> findAllById(String id);
    static List<Recipe> findBy(TextCriteria criteria);

}
