package com.menumer.repository;

import com.menumer.model.Ingredient;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface IngredientRepository extends MongoRepository<Ingredient, String> {}
