package com.platzi.market.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class Category {
    @JsonProperty("id")
    @Schema( example = "1", minimum = "1")
    private int categoryId;
    @Schema(example = "Frutas y verduras")
    private String category;
    private boolean active;
}
