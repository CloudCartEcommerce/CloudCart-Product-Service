package com.cloudcart.product.dto;

import com.cloudcart.product.enums.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductResponseDto {

    private UUID id;
    private String name;
    private String sku;
    private Category category;
    private BigDecimal price;
    private String description;


}
