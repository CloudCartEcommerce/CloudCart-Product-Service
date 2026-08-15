package com.cloudcart.product.repository;

import com.cloudcart.product.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    public boolean existsBySku(String sku);                     // Returns true if any product exists with the given sku     => Select * from Product where sku = given_sku
    public boolean existsBySkuAndIdNot(String sku, UUID id);    // Returns true if any product exists with the given sku and not the id   => Select * from Product where sku = given_sku and id not = given_id
}
