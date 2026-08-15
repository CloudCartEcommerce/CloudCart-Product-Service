package com.cloudcart.product.service;

import java.util.*;

import com.cloudcart.product.exceptions.AnotherProductFoundWithTheSkuException;
import com.cloudcart.product.exceptions.CategoryNullException;
import com.cloudcart.product.exceptions.ProductAlreadyExistsException;
import com.cloudcart.product.exceptions.ProductNotFoundException;
import com.cloudcart.product.model.Product;

public interface ProductService {

    Product createProduct(Product product, String idempotencyKey) throws ProductAlreadyExistsException;
    List<Product> getAllProducts();
    Product getProduct(UUID id) throws ProductNotFoundException;
    Product updateProduct(UUID id, Product product) throws ProductNotFoundException, AnotherProductFoundWithTheSkuException;
    void deleteProduct(UUID id) throws ProductNotFoundException;
}
