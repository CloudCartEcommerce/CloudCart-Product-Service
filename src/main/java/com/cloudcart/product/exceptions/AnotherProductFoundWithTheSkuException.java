package com.cloudcart.product.exceptions;

public class AnotherProductFoundWithTheSkuException extends RuntimeException{
    public AnotherProductFoundWithTheSkuException(String message) {
        super(message);
    }
}
