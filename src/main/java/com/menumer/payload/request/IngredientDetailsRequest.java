package com.menumer.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDetailsRequest {
    private String id;
    private String ingredientName;
    private Integer quantity;
    private String quantityUnit;
    private String otherName;
}
