package com.menumer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document(value = "ingredient")
public class Ingredient {
    @Id
    private String Id;
    private String ingredientName;
    private String otherName;
    @LastModifiedDate
    private Date last_modified_date;
    @CreatedDate
    private Date created_date;
}
