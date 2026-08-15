package com.cloudcart.product.dto;

import lombok.Data;

@Data
public class ErrorResponseDto {
    private String message;
    private String error;
}
