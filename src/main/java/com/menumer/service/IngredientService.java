package com.menumer.service;

import com.menumer.model.Ingredient;
import com.menumer.model.IngredientDetails;
import com.menumer.model.enums.QuantityUnit;
import com.menumer.payload.request.IngredientDetailsRequest;
import com.menumer.repository.IngredientDetailsRepository;
import com.menumer.repository.IngredientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService {
    private IngredientRepository ingredientRepository;
    private IngredientDetailsRepository ingredientDetailsRepository;

    public List<IngredientDetails> saveIngredients(List<IngredientDetailsRequest> ingredientDetailsRequest) {
        List<IngredientDetails> ingredientDetails = new ArrayList<>();
        for (IngredientDetailsRequest ingReq : ingredientDetailsRequest) {
            Ingredient ingredient = Ingredient.builder()
                    .ingredientName(ingReq.getIngredientName())
                    .otherName(ingReq.getOtherName())
                    .build();
            ingredientRepository.save(ingredient);
            IngredientDetails details = IngredientDetails.builder()
                    .ingredient(ingredient)
                    .quantity(ingReq.getQuantity())
                    .quantityUnit(QuantityUnit.getQuantityUnit(ingReq.getQuantityUnit()))
                    .build();
            ingredientDetails.add(details);
        }
        ingredientDetailsRepository.saveAll(ingredientDetails);
        return ingredientDetails;
    }
}
