package com.menumer.repository;

import com.menumer.model.IngredientDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IngredientDetailsRepository extends MongoRepository<IngredientDetails, String> { }

