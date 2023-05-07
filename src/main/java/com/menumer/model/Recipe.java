package com.menumer.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.menumer.model.enums.Action;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "recipe")
public class Recipe {
    @Id
    private String id;
    @JsonBackReference
    @DocumentReference
    private User user;
    @TextIndexed
    private String recipeName;
    @TextIndexed
    @DocumentReference
    private List<IngredientDetails> ingredientDetails;
    @TextIndexed
    private String description;
    private Integer rating;
    private Integer numberOfLikes;
    @DocumentReference
    private List<Comment> comments;
    private Action action;
    @LastModifiedDate
    private Date lastModifiedDate;
    @CreatedDate
    private Date createdDate;


    // TODO: 11/18/22 Include status for recipe e.g draft 
}
