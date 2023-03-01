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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(value = "rating")
public class Rating {
    @Id
    private String id;
    private String rating_name;
    private Integer rating_stars;
    @LastModifiedDate
    private Date last_modified_date;
    @CreatedDate
    private Date created_date;
}
