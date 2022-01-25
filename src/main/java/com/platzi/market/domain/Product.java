package com.platzi.market.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Product {
    @JsonProperty("id")
    @Schema(example = "51", minimum = "1")
    private int productId;
    @Schema(example = "Pitaya")
    private String name;
    @JsonProperty("category_id")
    @Schema(example = "1", minimum = "1")
    private int categoryId;
    @Schema(example = "12.55")
    private double price;
    @Schema( example = "34")
    private int stock;
    private boolean active;
    private Category category;
}
