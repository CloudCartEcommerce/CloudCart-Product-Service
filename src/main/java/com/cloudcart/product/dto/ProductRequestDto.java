package com.cloudcart.product.dto;

import com.cloudcart.product.enums.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequestDto {

    @NotBlank
    private String sku;
    @NotBlank
    private String name;
    @NotNull
    private Category category;
    @NotNull
    private BigDecimal price;
    @NotBlank
    private String description;
}
