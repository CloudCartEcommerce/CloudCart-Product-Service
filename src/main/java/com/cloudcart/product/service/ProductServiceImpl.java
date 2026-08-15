package com.cloudcart.product.service;

import com.cloudcart.product.exceptions.AnotherProductFoundWithTheSkuException;
import com.cloudcart.product.exceptions.ProductAlreadyExistsException;
import com.cloudcart.product.exceptions.ProductNotFoundException;
import com.cloudcart.product.model.IdempotencyRecord;
import com.cloudcart.product.model.Product;
import com.cloudcart.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;              // The dependency shouldn't change after construction
    private final IdempotencyService idempotencyService;

    public ProductServiceImpl(ProductRepository productRepository, IdempotencyService idempotencyService) {
        this.productRepository = productRepository;
        this.idempotencyService = idempotencyService;
    }

    @Override
    @Transactional // The adition of idempotency and creation of product should happen as an atomic operation
    public Product createProduct(Product product, String idempotencyKey)  {

        /*
        The null validation check is done in our DTO, hence checking whether each value is null or not is not needed at this layer anymore.

         */

        // Checking if the idempotencyKey already exists
        Optional<IdempotencyRecord> optionalIdempotencyRecord = idempotencyService.findByKey(idempotencyKey);
        if (optionalIdempotencyRecord.isPresent()) {
            // The product for this was already created and the response might have been lost. Hence we just return the product already created
            IdempotencyRecord idempotencyRecord = optionalIdempotencyRecord.get();
            return productRepository.findById(idempotencyRecord.getProductId())
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        }
        if(productRepository.existsBySku(product.getSku())) {
            throw new ProductAlreadyExistsException("Product with SKU " + product.getSku() + " already exists");
        }


        Product newProduct = productRepository.save(product);
        idempotencyService.saveRecord(idempotencyKey,newProduct.getId());
        return newProduct;

    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(UUID id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()) {
            return optionalProduct.get();
        }
        throw new ProductNotFoundException("No product with id " + id + " was found");
    }

    @Override
    public Product updateProduct(UUID id, Product product){
        /*
        1. Check whether a product with this ID exists or not.
        2. Check whether another product with the provided product's sku exists or not.
        3. If both the above conditions are correct, then only update the product.
         */

        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty())
        {
            throw new ProductNotFoundException("No product with id ->" + id);
        }
        if(productRepository.existsBySkuAndIdNot(product.getSku(), id))
        {
            throw new AnotherProductFoundWithTheSkuException("Another Product already present with the same sku. SKU should be unique");
        }
        Product existingProduct = optionalProduct.get();
        existingProduct.setSku(product.getSku());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        return productRepository.save(existingProduct);


    }

    @Override
    public void deleteProduct(UUID id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty())
        {
            throw new ProductNotFoundException("No Product with id -> " + id + " is Present");
        }
        productRepository.deleteById(id);

    }
}
