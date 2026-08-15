package com.cloudcart.product.controller;

import com.cloudcart.product.dto.ProductRequestDto;
import com.cloudcart.product.dto.ProductResponseDto;
import com.cloudcart.product.mapper.ProductMapper;
import com.cloudcart.product.model.Product;
import com.cloudcart.product.service.ProductService;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }


    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getAllProducts()
    {
        ResponseEntity<List<ProductResponseDto>> response;
        List<Product> products = productService.getAllProducts();
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();
        for(Product product : products)
        {
            ProductResponseDto productResponseDto = productMapper.productToProductResponseDto(product);
            productResponseDtos.add(productResponseDto);
        }
        response = new ResponseEntity<>(productResponseDtos, HttpStatus.OK);
        return response;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable UUID id) {
        ResponseEntity<ProductResponseDto> response;

        Product product = productService.getProduct(id);
        ProductResponseDto productResponseDto = productMapper.productToProductResponseDto(product);
        response = new ResponseEntity<>(productResponseDto, HttpStatus.OK);
        return response;
    }

    @PostMapping
    public ResponseEntity<ProductResponseDto> createProduct(@Valid @RequestBody ProductRequestDto productRequestDto, @RequestHeader("Idempotency-Key") String idempotencyKey) {
        ResponseEntity<ProductResponseDto> response;
        Product product = productService.createProduct(productMapper.requestDtoToProduct(productRequestDto), idempotencyKey);
        ProductResponseDto productResponseDto = productMapper.productToProductResponseDto(product);
        response = new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponseDto> updateProduct(@Valid @RequestBody ProductRequestDto productRequestDto, @PathVariable UUID id)  {
        ResponseEntity<ProductResponseDto> response;
        Product product = productService.updateProduct(id, productMapper.requestDtoToProduct(productRequestDto));
        ProductResponseDto productResponseDto = productMapper.productToProductResponseDto(product);
        response = new ResponseEntity<>(productResponseDto, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>  deleteProduct(@PathVariable UUID id)  {
        ResponseEntity<Void> response;
        productService.deleteProduct(id);
        response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return response;
    }




}
