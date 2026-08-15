package com.cloudcart.product.mapper;

import com.cloudcart.product.dto.ProductRequestDto;
import com.cloudcart.product.dto.ProductResponseDto;
import com.cloudcart.product.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public  Product requestDtoToProduct(ProductRequestDto request)
    {
        Product product = new Product();
        product.setSku(request.getSku());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setDescription(request.getDescription());
        product.setName(request.getName());

        return product;
    }

    public ProductResponseDto productToProductResponseDto(Product product)
    {
        if(product == null)
        {
            return null;
        }
        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setSku(product.getSku());
        productResponseDto.setCategory(product.getCategory());
        productResponseDto.setPrice(product.getPrice());
        productResponseDto.setDescription(product.getDescription());
        productResponseDto.setName(product.getName());
        productResponseDto.setId(product.getId());

        return productResponseDto;
    }

}
